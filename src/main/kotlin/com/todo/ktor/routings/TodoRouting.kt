package com.todo.ktor.routings

import com.todo.ktor.repositories.TodoRepository
import com.todo.ktor.vo.Todo
import com.todo.ktor.vo.TodoDraft
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

import io.ktor.routing.*



fun Routing.configTodoRouting() {

    val repository = TodoRepository()

    get("/todos") {
        val todos = repository.getTodos().map {
            Todo(it.id, it.title, it.done)
        }
        call.respond(todos)
    }

    get("/todos/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val todo = repository.getTodo(id)?.let { Todo(it.id, it.title, it.done) }
        if(todo == null) {
            call.respond(HttpStatusCode.NotFound)
        } else {
            call.respond(todo)
        }
    }

    post("/todos") {
        val draft = call.receive<TodoDraft>()
        val isSaved = repository.addTodo(draft)
        if (isSaved) {
            call.respond(HttpStatusCode.Created)
        } else {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    put("/todos/{id}") {
        val draft = call.receive<TodoDraft>()
        val id = call.parameters["id"]?.toLongOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@put
        }
        val isUpdated = repository.updateTodo(id, draft)
        if (isUpdated) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    delete("/todos/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        val isDeleted = repository.removeTodo(id)
        if (isDeleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}