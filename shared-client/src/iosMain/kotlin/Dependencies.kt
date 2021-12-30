package com.example.multiplatform.shared.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val httpClientEngine: HttpClientEngine = Darwin.create()
actual val host: String = "127.0.0.1"

