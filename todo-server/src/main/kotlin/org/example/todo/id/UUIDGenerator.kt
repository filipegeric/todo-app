package org.example.todo.id

import java.util.UUID

object UUIDGenerator : IdGenerator {
    override fun generate(): String = UUID.randomUUID().toString()
}
