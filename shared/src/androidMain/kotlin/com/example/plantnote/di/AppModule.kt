package com.example.plantnote.di

import android.content.Context
import com.example.plantnote.core.data.DatabaseDriverFactory
import com.example.plantnote.database.PlantDatabase
import com.example.plantnote.plants.data.SqlDelightPlantDataSource
import com.example.plantnote.plants.domain.PlantDataSource


actual class AppModule(
    private val context: Context
) {
    actual val plantDatasource: PlantDataSource by lazy {
        SqlDelightPlantDataSource(
            db = PlantDatabase(
                driver = DatabaseDriverFactory(context).create()
            )
        )
    }
}