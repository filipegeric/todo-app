package org.example.todo.todos

data class TodoDto(
    val id: String,
    val title: String,
    val isDone: Boolean,
    val category: String?,
    val creatorId: String,
)

fun Todo.toDto() = TodoDto(id, title, isDone, category, creatorId)
