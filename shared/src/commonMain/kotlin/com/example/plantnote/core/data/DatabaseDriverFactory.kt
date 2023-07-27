package com.example.plantnote.core.data

import com.squareup.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}