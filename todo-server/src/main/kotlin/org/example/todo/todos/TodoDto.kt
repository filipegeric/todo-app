package org.example.todo.todos

data class TodoDto(
    val id: String,
    val title: String,
    val isDone: Boolean,
    val description: String?,
    val category: String?,
    val creatorId: String,
)

fun Todo.toDto() = TodoDto(id, title, isDone, description, category, creatorId)
