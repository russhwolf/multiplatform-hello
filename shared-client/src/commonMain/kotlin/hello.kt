package com.example.multiplatform.shared.client

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class) // Don't use GlobalScope in real code!
fun hello(callback: (String) -> Unit) = GlobalScope.launch(Dispatchers.Main) {
    val message = try {
        ApiClient().getMessage().value
    } catch (e: Throwable) {
        e.message ?: "An error occurred"
    }
    callback(message)
}
