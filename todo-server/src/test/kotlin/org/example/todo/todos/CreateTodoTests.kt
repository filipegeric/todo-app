package org.example.todo.todos

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.todo.auth.User

@DisplayName("Creating a todo")
class CreateTodoTests : StringSpec() {
    private val service = TodosModule().todosService()

    init {
        "returns created todo" {
            val request = CreateTodoRequest(title = "Clean up")
            val user = User(id = "1")

            val createdTodo = service.createTodo(request, user)

            createdTodo.title shouldBe "Clean up"
            createdTodo.creatorId shouldBe "1"
        }

        "returns todo with a unique id" {
            val request = CreateTodoRequest(title = "Clean up")
            val user = User(id = "1")

            val firstTodo = service.createTodo(request, user)
            val secondTodo = service.createTodo(request, user)

            firstTodo.id shouldNotBe secondTodo.id
        }

        "enables listing todos" {
            val user = User(id = "1")
            val request1 = CreateTodoRequest(title = "Clean up")
            val request2 = CreateTodoRequest(title = "Do homework")

            service.createTodo(request1, user)
            service.createTodo(request2, user)

            service.findTodos(user).size shouldBe 2
        }
    }
}
