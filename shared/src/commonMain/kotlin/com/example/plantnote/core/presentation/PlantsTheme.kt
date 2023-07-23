package com.example.plantnote.core.presentation

import androidx.compose.runtime.Composable

expect fun PlantsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    context: @Composable () -> Unit
)