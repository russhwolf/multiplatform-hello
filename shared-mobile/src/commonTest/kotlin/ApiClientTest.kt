package com.example.multiplatform.shared

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiClientTest {
    private val mockEngine = MockEngine {
        if (it.url.encodedPath == "message") {
            respond(
                content = """{"value":"test"}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        } else {
            respond(
                content = "",
                status = HttpStatusCode.NotFound
            )
        }
    }

    private val apiClient = ApiClient(mockEngine)

    @Test
    fun getMessage() = runBlocking {
        val message = apiClient.getMessage()
        assertEquals(
            expected = Message("test"),
            actual = message
        )
    }
}
