package com.example.multiplatform.android

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.multiplatform.shared.hello

class MainActivity : Activity() {

    private val textView by lazy { findViewById<TextView>(R.id.text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello {
            textView.text = it
        }
    }
}
