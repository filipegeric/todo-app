package org.example.todo.todos

class InMemoryTodosRepository : TodosRepository {
    private val todos: MutableMap<String, MutableList<Todo>> = mutableMapOf()

    override fun findByUserId(userId: String): List<Todo> {
        return todos[userId]?.toList() ?: emptyList()
    }

    override fun save(todo: Todo) {
        todos.putIfAbsent(todo.creatorId, mutableListOf())
        todos[todo.creatorId]!!.add(todo)
    }
}
