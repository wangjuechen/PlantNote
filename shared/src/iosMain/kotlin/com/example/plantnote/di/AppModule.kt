package com.example.plantnote.di

import com.example.plantnote.core.data.DatabaseDriverFactory
import com.example.plantnote.core.data.ImageStorage
import com.example.plantnote.database.PlantDatabase
import com.example.plantnote.plants.data.SqlDelightPlantDataSource
import com.example.plantnote.plants.domain.PlantDataSource


actual class AppModule {
    actual val plantDatasource: PlantDataSource by lazy {
        SqlDelightPlantDataSource(
            db = PlantDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
}