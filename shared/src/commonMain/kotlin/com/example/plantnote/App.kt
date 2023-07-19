package com.example.plantnote

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HomePage() {
    var text by remember { mutableStateOf("Loading") }
    LaunchedEffect(true) {
        text = try {
            Greeting().greet()
        } catch (e: Exception) {
            e.message ?: "error"
        }
    }
    GreetingView(text)
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
