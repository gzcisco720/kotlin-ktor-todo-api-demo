package com.todo.ktor.plugins

import com.todo.ktor.common.DatabaseManager
import io.ktor.application.*


fun Application.configureFlyway() {
    val dbManager = DatabaseManager
    dbManager.initFlyWayMigration()

}
