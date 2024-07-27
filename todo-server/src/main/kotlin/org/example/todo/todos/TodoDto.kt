package org.example.todo.todos

data class TodoDto(val id: String, val title: String, val creatorId: String)

fun Todo.toDto() = TodoDto(id, title, creatorId)
