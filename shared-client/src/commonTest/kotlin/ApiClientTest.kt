package com.example.multiplatform.shared.client

import com.example.multiplatform.shared.Message
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ApiClientTest {
    @Test
    fun getMessage() = runTest {
        val mockEngine = MockEngine {
            if (it.url.encodedPath == "/message") {
                respond(
                    content = """{"value":"test"}""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respondBadRequest()
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
    fun apiError() = runTest {
        val mockEngine = MockEngine {
            respondBadRequest()
        }
        val apiClient = ApiClient(mockEngine)

        assertFailsWith<ClientRequestException> {
            apiClient.getMessage()
        }
    }
}
