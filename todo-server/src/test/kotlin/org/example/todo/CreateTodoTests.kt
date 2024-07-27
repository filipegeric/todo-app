package org.example.todo

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
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
    }
}
