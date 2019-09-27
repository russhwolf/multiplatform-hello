package com.russhwolf.settings.example.android

import android.app.Activity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import com.example.multiplatform.shared.SettingsClient
import com.example.multiplatform.shared.hello
import com.russhwolf.settings.AndroidSettings

class MainActivity : Activity() {

    private val textView by lazy { findViewById<TextView>(R.id.text) }

    private val settingsClient by lazy {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val settings = AndroidSettings(sharedPreferences)
        SettingsClient(settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cached = settingsClient.cache
        if (cached != null) {
            textView.text = "$cached*"
        }

        hello {
            textView.text = it
            settingsClient.cache = it
        }
    }
}
