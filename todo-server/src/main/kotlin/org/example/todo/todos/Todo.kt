package org.example.todo.todos

data class Todo(
    val id: String,
    val title: String,
    val category: String?,
    val creatorId: String,
)
