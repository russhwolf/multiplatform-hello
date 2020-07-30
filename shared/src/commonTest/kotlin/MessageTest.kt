package com.example.multiplatform.shared

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class MessageTest {
    private val message = Message("test")
    private val json = """{"value":"test"}"""

    @Test
    fun messageToJson() {
        assertEquals(
            expected = json,
            actual = Json.encodeToString(Message.serializer(), message)
        )
    }

    @Test
    fun jsonToMessage() {
        assertEquals(
            expected = message,
            actual = Json.decodeFromString(Message.serializer(), json)
        )
    }
}
