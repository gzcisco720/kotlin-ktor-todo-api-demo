package com.todo.ktor.vo

data class Todo(val id: Long, val title:String, val done:Boolean)

data class TodoDraft(val title:String, val done:Boolean)