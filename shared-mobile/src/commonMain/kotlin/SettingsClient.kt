package com.example.multiplatform.shared

import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableString
import com.russhwolf.settings.string

class SettingsClient(settings: Settings) {
    var cache by settings.nullableString()
}
