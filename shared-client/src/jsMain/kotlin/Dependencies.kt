package com.example.multiplatform.shared.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual val httpClientEngine: HttpClientEngine = Js.create()
actual val urlHost: String = "127.0.0.1"
