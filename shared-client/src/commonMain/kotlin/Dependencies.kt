package com.example.multiplatform.shared.client

import io.ktor.client.engine.HttpClientEngine

expect val httpClientEngine: HttpClientEngine
expect val host: String
