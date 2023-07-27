package com.example.plantnote.core.data

import android.content.Context
import com.example.plantnote.database.PlantDatabase
import com.example.plantnote.plants.domain.PlantDataSource
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            PlantDatabase.Schema,
            context,
            "plant.db"
        )
    }
}