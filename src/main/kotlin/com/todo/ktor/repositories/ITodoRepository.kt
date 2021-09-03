package com.todo.ktor.repositories

import com.todo.ktor.entities.TodoEntity
import com.todo.ktor.vo.Todo
import com.todo.ktor.vo.TodoDraft

interface ITodoRepository {
   suspend fun getTodos(): List<TodoEntity>
   suspend fun getTodo(id: Long): TodoEntity?
   suspend fun addTodo(draft: TodoDraft): Boolean
   suspend fun updateTodo(id: Long, draft: TodoDraft): Boolean
   suspend fun removeTodo(id: Long): Boolean
}