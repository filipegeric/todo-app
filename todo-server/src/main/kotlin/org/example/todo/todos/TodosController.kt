package org.example.todo.todos

import org.example.todo.auth.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodosController(private val service: TodosService) {

    @PostMapping
    fun create(
        @RequestBody request: CreateTodoRequest,
        user: User,
    ) = service.createTodo(request, user)

    @GetMapping
    fun getTodos(user: User) = service.findTodos(user)

    @PatchMapping("/{id}")
    fun updateTodo(
        @PathVariable id: String,
        @RequestBody request: UpdateTodoRequest,
        user: User,
    ) = service.updateTodo(request, user)
}
