package org.example.todo.todos

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.todo.auth.User

@DisplayName("Updating a todo")
class UpdateTodoTests : StringSpec() {
    private val service = TodosModule().todosService()
    private val user = User("john")

    init {
        "throws TodoNotFoundException when updating a non-existent todo" {
            val exception =
                shouldThrow<TodoNotFoundException> {
                    service.updateTodo(
                        UpdateTodoRequest(id = "non-existent", title = "do sth"),
                        user,
                    )
                }
            exception.id shouldBe "non-existent"
        }

        "returns updated todo" {
            val createdTodo = service.createTodo(
                CreateTodoRequest("clean up"), user
            )

            val updatedTodo =
                service.updateTodo(
                    UpdateTodoRequest(
                        id = createdTodo.id,
                        title = "clean down",
                        description = "house",
                        category = "cat 2",
                        isDone = true,
                    ),
                    user,
                )

            createdTodo.id shouldBe updatedTodo.id
            updatedTodo.title shouldBe "clean down"
            updatedTodo.description shouldBe "house"
            updatedTodo.category shouldBe "cat 2"
            updatedTodo.isDone shouldBe true
        }
    }
}
