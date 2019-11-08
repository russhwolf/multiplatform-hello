package com.example.multiplatform.shared

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@UseExperimental(UnstableDefault::class)
class MessageTest {
    private val message = Message("test")
    private val json = """{"value":"test"}"""

    @Test
    fun messageToJson() {
        assertEquals(
            expected = json,
            actual = Json.stringify(Message.serializer(), message)
        )
    }

    @Test
    fun jsonToMessage() {
        assertEquals(
            expected = message,
            actual = Json.parse(Message.serializer(), json)
        )
    }
}
