package org.example.todo

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("Adding two numbers")
class ExampleTest : StringSpec({
    "works" {
        1 + 1 shouldBe 2
    }
})
