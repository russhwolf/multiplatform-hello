package com.example.multiplatform.shared.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val httpClientEngine: HttpClientEngine = Js.create()
actual val host: String = "127.0.0.1"

actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
