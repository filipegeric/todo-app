package org.example.todo.todos

import org.example.todo.id.UUIDGenerator

class TodosModule {
    fun todosService(): TodosService = TodosService(InMemoryTodosRepository(), UUIDGenerator)
}
