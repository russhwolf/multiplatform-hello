package com.example.multiplatform.shared

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun hello(callback: (String) -> Unit) = GlobalScope.launch(mainDispatcher) {
    val message = try {
        ApiClient().getMessage().value
    } catch (e: Throwable) {
        e.message ?: "An error occurred"
    }
    callback(message)
}
