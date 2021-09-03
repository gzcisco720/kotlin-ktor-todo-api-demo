package com.todo.ktor.plugins

import com.todo.ktor.routings.configTodoRouting
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*


fun Application.configureRouting() {

    routing {
        static("/static") {
            resources("static")
        }
        configTodoRouting()
    }
}
