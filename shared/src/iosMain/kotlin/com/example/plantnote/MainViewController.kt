package com.example.plantnote

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.example.plantnote.core.presentation.ImagePickerFactory
import com.example.plantnote.di.AppModule

fun MainViewController() = ComposeUIViewController {
//    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
//            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = false,
        dynamicColor = false,
        appModule = AppModule(),
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker()
    )
}