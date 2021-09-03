package com.todo.ktor.repositories

import com.todo.ktor.common.DatabaseManager
import com.todo.ktor.entities.TodoEntity
import com.todo.ktor.entities.todo
import com.todo.ktor.vo.TodoDraft
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.removeIf
import org.ktorm.entity.toList

class TodoRepository: ITodoRepository {

    override suspend fun getTodos(): List<TodoEntity> {
        return DatabaseManager.dbQuery {
            todo.toList()
        }
    }

    override suspend fun getTodo(id: Long): TodoEntity? {
        return DatabaseManager.dbQuery {
            todo.find { it.id eq id }
        }
    }

    override suspend fun addTodo(draft: TodoDraft): Boolean {
        return DatabaseManager.dbQuery {
            val entity = TodoEntity {
                title = draft.title
                done = draft.done
            }
            todo.add(entity) >0
        }
    }

    override suspend fun updateTodo(id: Long, draft: TodoDraft): Boolean {
        return DatabaseManager.dbQuery {
            val found = todo.find { it.id eq id } ?: return@dbQuery false
            found.title = draft.title
            found.done = draft.done
            return@dbQuery found.flushChanges() > 0
        }
    }

    override suspend fun removeTodo(id: Long): Boolean {
        return DatabaseManager.dbQuery {
            todo.removeIf { it.id eq id } > 0
        }
    }

}