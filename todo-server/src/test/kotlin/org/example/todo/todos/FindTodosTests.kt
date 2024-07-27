package org.example.todo.todos

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainInOrder
import org.example.todo.auth.User

@DisplayName("Finding todos")
class FindTodosTests : StringSpec() {
    private val service = TodosModule().todosService()

    init {
        "returns only todos created by the user" {
            val john = User(id = "john")
            val jane = User(id = "jane")

            service.createTodo(CreateTodoRequest("john 1"), john)
            service.createTodo(CreateTodoRequest("jane 1"), jane)
            service.createTodo(CreateTodoRequest("john 2"), john)

            val johnsTodos = service.findTodos(john)
            val janesTodos = service.findTodos(jane)
            johnsTodos.map { it.title } shouldContainInOrder listOf("john 1", "john 2")
            janesTodos.map { it.title } shouldContainInOrder listOf("jane 1")
        }
    }
}
