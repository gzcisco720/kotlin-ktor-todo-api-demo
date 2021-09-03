package com.todo.ktor.repositories

import com.todo.ktor.vo.Todo
import com.todo.ktor.vo.TodoDraft

interface ITodoRepository {
   suspend fun getTodos(): List<Todo>
   suspend fun addTodo(draft: TodoDraft): Todo
}