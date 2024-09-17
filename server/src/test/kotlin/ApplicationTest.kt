package com.example.multiplatform.server

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun GET_message() {
        testApplication {
            environment {
                config = ApplicationConfig("Application.conf")
            }

            val response = client.get("/message")
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("""{"value":"hello"}""", response.body())
        }
    }
}
