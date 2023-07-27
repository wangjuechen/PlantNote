package com.example.plantnote

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.plantnote.core.presentation.PlantsTheme
import com.example.plantnote.di.AppModule
import com.example.plantnote.plants.presentation.PlantsListViewModel
import com.example.plantnote.plants.presentation.components.PlantListScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
    PlantsTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        val viewModel = getViewModel(
            key = "plant-list-screen",
            factory = viewModelFactory {
                PlantsListViewModel(appModule.plantDatasource)
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PlantListScreen(
                state = state,
                newPlant = viewModel.newPlant,
                onEvent = viewModel::onEvent
            )
        }
    }

}

