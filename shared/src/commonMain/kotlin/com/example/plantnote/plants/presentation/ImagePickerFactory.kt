package com.example.plantnote.plants.presentation

import androidx.compose.runtime.Composable
import com.example.plantnote.core.presentation.ImagePicker


expect class ImagePickerFactory {

    @Composable
    fun createPicker(): ImagePicker
}