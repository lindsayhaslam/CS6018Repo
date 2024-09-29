package com.example.lab5marble.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define your color schemes
private val LightColorScheme = lightColorScheme(
    // Add your light colors here
)

private val DarkColorScheme = darkColorScheme(
    // Add your dark colors here
)

// Theme function
@Composable
fun Lab5MarbleTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
