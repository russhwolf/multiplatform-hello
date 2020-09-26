package com.example.multiplatform.shared.client

import com.example.multiplatform.shared.Message
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ApiClientTest {
    @Test
    fun getMessage(): Unit = runBlocking {
        val mockEngine = MockEngine {
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
        val apiClient = ApiClient(mockEngine)

        val message = apiClient.getMessage()
        assertEquals(
            expected = Message("test"),
            actual = message
        )
    }

    @Test
    fun apiError(): Unit = runBlocking {
        val mockEngine = MockEngine {
            respond(
                content = "",
                status = HttpStatusCode.NotFound
            )
        }
        val apiClient = ApiClient(mockEngine)

        assertFailsWith<ClientRequestException> {
            apiClient.getMessage()
        }
    }
}
