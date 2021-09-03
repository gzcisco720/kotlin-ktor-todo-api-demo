package com.todo.ktor.common

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.flywaydb.core.Flyway
import org.ktorm.database.Database


object DatabaseManager {

    private val config = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = config.propertyOrNull("ktor.datasource.jdbcUrl")?.getString()
    private val user = config.propertyOrNull("ktor.datasource.user")?.getString()
    private val password = config.propertyOrNull("ktor.datasource.password")?.getString()
    val db =  Database.connect(getHikariPool())

    private val flyway by lazy {
        Flyway.configure().dataSource(dbUrl, user, password).load()
    }

    fun initFlyWayMigration() {
        flyway.migrate()
    }

    private fun getHikariPool(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "com.mysql.cj.jdbc.Driver"
        config.jdbcUrl = dbUrl
        config.username = user
        config.password = password
        config.maximumPoolSize = 12
        config.minimumIdle = 4
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: Database.() -> T): T =
        withContext(Dispatchers.IO) {
            db.useTransaction {
                db.block()
            }
        }
}