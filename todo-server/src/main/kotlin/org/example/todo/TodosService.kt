package org.example.todo

import org.example.todo.auth.User


class TodosService {
    fun createTodo(request: CreateTodoRequest, user: User): TodoDto {
        return TodoDto(id = "a", title = request.title, creatorId = user.id)
    }
}

data class CreateTodoRequest(val title: String)


