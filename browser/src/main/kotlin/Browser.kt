package com.example.multiplatform.js

import com.example.multiplatform.shared.client.hello
import kotlin.browser.document

fun main() {
    hello {
        document.body!!.textContent = it
    }
}
