package com.example.multiplatform.shared

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class MessageTest {
    @UnstableDefault
    @Test fun messageSerialization() {
        val message = Message("test")
        val json = """{"value":"test"}"""

        assertEquals(
            expected = json,
            actual = Json.stringify(Message.serializer(), message)
        )

        assertEquals(
            expected = message,
            actual = Json.parse(Message.serializer(), json)
        )
    }
}
