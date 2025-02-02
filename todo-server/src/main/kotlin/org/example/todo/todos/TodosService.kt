package org.example.todo.todos

import org.example.todo.auth.User
import org.example.todo.id.IdGenerator

class TodosService(
    private val repository: TodosRepository,
    private val idGenerator: IdGenerator,
) {
    fun createTodo(
        request: CreateTodoRequest,
        user: User,
    ): TodoDto {
        val todo = Todo(
            id = idGenerator.generate(),
            title = request.title,
            description = request.description,
            category = request.category,
            isDone = false,
            creatorId = user.id,
        )
        repository.save(todo)
        return todo.toDto()
    }

    fun findTodos(user: User): List<TodoDto> = repository.findByUserId(user.id).map { it.toDto() }

    fun updateTodo(
        request: UpdateTodoRequest,
        user: User,
    ): TodoDto {
        val todos = repository.findByUserId(user.id)
        val todo = todos.find { it.id == request.id } ?: throw TodoNotFoundException(request.id)

        val updatedTodo = todo.copy(
            title = request.title ?: todo.title,
            description = request.description ?: todo.description,
            category = request.category ?: todo.category,
            isDone = request.isDone ?: todo.isDone,
        )

        repository.save(updatedTodo)

        return updatedTodo.toDto()
    }
}

data class CreateTodoRequest(
    val title: String,
    val description: String? = null,
    val category: String? = null,
)

data class UpdateTodoRequest(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val isDone: Boolean? = null,
)
