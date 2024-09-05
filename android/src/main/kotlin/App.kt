package com.example.multiplatform.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multiplatform.shared.client.hello

@Composable
fun App() {
    val text = remember { mutableStateOf("Loading") }

    LaunchedEffect(Unit) {
        hello {
            text.value = it
        }
    }

    MaterialTheme {
        Box(Modifier.padding(16.dp).fillMaxSize()) {
            Text(text.value)
        }
    }
}

@Preview
@Composable
fun App_Preview() {
    App()
}
