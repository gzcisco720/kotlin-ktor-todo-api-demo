package com.todo.ktor.repositories

import com.todo.ktor.common.DatabaseManager
import com.todo.ktor.entities.TodoEntity
import com.todo.ktor.entities.todo
import com.todo.ktor.vo.Todo
import com.todo.ktor.vo.TodoDraft
import org.ktorm.entity.add
import org.ktorm.entity.toList

class TodoRepository: ITodoRepository {

    override suspend fun getTodos(): List<Todo> {
        return DatabaseManager.dbQuery {
            todo.toList()
        }.map { Todo(it.id, it.title, it.done) }
    }

    override suspend fun addTodo(draft: TodoDraft): Todo {
        val savedId = DatabaseManager.dbQuery {
            val entity = TodoEntity {
                title = draft.title
                done = draft.done
            }
            todo.add(entity)
        }
        return Todo(savedId.toLong(), draft.title, draft.done)
    }

}