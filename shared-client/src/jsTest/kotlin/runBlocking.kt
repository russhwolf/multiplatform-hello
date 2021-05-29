package com.example.multiplatform.shared.client

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.coroutines.CoroutineContext

@OptIn(DelicateCoroutinesApi::class)
actual fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): dynamic =
     GlobalScope.promise(context) { block() }
