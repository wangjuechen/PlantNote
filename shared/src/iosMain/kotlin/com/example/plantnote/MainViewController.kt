package com.example.plantnote

import androidx.compose.ui.window.ComposeUIViewController
import com.example.plantnote.di.AppModule

fun MainViewController() = ComposeUIViewController {
    App(darkTheme = false, dynamicColor = false, appModule = AppModule())
//    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
//            UIUserInterfaceStyle.UIUserInterfaceStyleDark
}