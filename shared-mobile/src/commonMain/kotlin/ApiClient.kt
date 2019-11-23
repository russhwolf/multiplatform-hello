package com.example.multiplatform.shared

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol

class ApiClient(engine: HttpClientEngine = httpClientEngine) {
    private val httpClient = HttpClient(engine) {
        defaultRequest {
            url.protocol = URLProtocol.HTTP // Use HTTPS for real applications!
            url.host = host
            url.port = 8080
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging)
    }

    suspend fun getMessage(): Message = httpClient.get {
        url {
            encodedPath = "message"
        }
    }
}
