package com.example.multiplatform.shared

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

actual val httpClientEngine: HttpClientEngine = Ios.create()

actual val host: String = "127.0.0.1"
