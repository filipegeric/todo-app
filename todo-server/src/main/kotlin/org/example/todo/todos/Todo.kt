package org.example.todo.todos


data class Todo(
    val id: String,
    val title: String,
    val isDone: Boolean,
    val description: String?,
    val category: String?,
    val creatorId: String,
)
