package org.example.todo.todos

interface TodosRepository {
    fun findByUserId(userId: String): List<Todo>

    fun save(todo: Todo)
}
