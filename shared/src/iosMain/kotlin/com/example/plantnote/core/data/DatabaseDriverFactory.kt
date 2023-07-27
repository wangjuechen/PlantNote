package com.example.plantnote.core.data

import com.example.plantnote.database.PlantDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(PlantDatabase.Schema, "plant.db")
    }
}