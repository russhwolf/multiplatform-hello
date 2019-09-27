package com.example.multiplatform.server

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun GET_message() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/message").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("""{"value":"hello"}""", response.content)
            }
        }
    }
}
