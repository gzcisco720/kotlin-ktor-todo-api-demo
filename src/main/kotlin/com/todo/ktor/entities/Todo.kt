package com.todo.ktor.entities

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

interface TodoEntity: Entity<TodoEntity> {
    companion object: Entity.Factory<TodoEntity>()
    val id: Long
    var title: String
    var done: Boolean
}

object TodoTable: Table<TodoEntity>("todo") {
    val id = long("id").primaryKey().bindTo { it.id }
    val title = varchar("title").bindTo { it.title }
    val done = boolean("done").bindTo { it.done }
}

val Database.todo get() = this.sequenceOf(TodoTable)