package com.example.plantnote.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.plantnote.ui.theme.DarkColorScheme
import com.example.plantnote.ui.theme.LightColorScheme
import com.example.plantnote.ui.theme.Typography

@Composable
actual fun PlantsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    context: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = context
    )
}