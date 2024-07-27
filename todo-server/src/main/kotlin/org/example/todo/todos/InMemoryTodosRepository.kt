package org.example.todo.todos

class InMemoryTodosRepository : TodosRepository {
    private val todos: MutableMap<String, Todo> = mutableMapOf()

    override fun findByUserId(userId: String): List<Todo> {
        return todos.values.filter { it.creatorId == userId }
    }

    override fun save(todo: Todo) {
        todos[todo.id] = todo
    }
}
