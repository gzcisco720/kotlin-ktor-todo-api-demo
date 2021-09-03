package com.todo.ktor.routings

import com.todo.ktor.repositories.TodoRepository
import com.todo.ktor.vo.TodoDraft
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

import io.ktor.routing.*



fun Routing.configTodoRouting() {

    val repository = TodoRepository()

    get("/todos") {
        val todos = repository.getTodos()
        call.respond(todos)
    }

    post("/todos") {
        val draft = call.receive<TodoDraft>()
        val saved = repository.addTodo(draft)
        call.respond(saved)
    }
}