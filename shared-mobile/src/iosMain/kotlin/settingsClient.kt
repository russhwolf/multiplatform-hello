package com.example.multiplatform.shared

import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults

@Suppress("unused") // Used from Swift
val settingsClient by lazy { SettingsClient(AppleSettings(NSUserDefaults.standardUserDefaults)) }
