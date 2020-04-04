package com.example.multiplatform.shared.client

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineDispatcher

expect val httpClientEngine: HttpClientEngine
expect val host: String

expect val mainDispatcher: CoroutineDispatcher
