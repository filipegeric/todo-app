package org.example.todo.todos

import org.example.todo.id.UUIDGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TodosModule {
    @Bean
    fun todosService(): TodosService = TodosService(InMemoryTodosRepository(), UUIDGenerator)
}
