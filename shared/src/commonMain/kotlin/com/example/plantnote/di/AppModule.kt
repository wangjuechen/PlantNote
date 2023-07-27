package com.example.plantnote.di

import com.example.plantnote.plants.domain.PlantDataSource


expect class AppModule {
    val plantDatasource: PlantDataSource
}