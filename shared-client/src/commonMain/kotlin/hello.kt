package com.example.multiplatform.shared.client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun hello(callback: (String) -> Unit) = GlobalScope.launch(Dispatchers.Main) {
    val message = try {
        ApiClient().getMessage().value
    } catch (e: Throwable) {
        e.message ?: "An error occurred"
    }
    callback(message)
}
