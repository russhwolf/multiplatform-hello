package com.example.multiplatform.shared.client

import com.example.multiplatform.shared.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json

class ApiClient(engine: HttpClientEngine = httpClientEngine) {
    private val httpClient = HttpClient(engine) {
        defaultRequest {
            url.protocol = URLProtocol.HTTP // Use HTTPS for real applications!
            url.host = host
            url.port = 8080
        }
        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    suspend fun getMessage(): Message = httpClient.get {
        url {
            encodedPath = "message"
        }
    }.body()
}
