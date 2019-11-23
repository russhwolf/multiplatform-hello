package com.example.multiplatform.shared

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual val httpClientEngine: HttpClientEngine = Android.create()

// Android emulator forwards local network localhost to here.
// This will not work for physical device.
actual val host: String = "10.0.2.2"
