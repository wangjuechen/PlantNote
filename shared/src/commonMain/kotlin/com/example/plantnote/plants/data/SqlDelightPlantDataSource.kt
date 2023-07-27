package com.example.plantnote.plants.data

import com.example.plantnote.database.PlantDatabase
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.domain.PlantDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock


class SqlDelightPlantDataSource(db: PlantDatabase) : PlantDataSource {

    private val queries = db.plantQueries

    override fun getPlants(): Flow<List<Plant>> {
        return queries.getPlants().asFlow().mapToList()
            .map {
                it.map { plantEntity -> plantEntity.toPlant() }
            }
    }

    override fun gtStarredPlants(): Flow<List<Plant>> {
        return queries.getStarredPlants().asFlow().mapToList()
            .map {
                it.map { plantEntity -> plantEntity.toPlant() }
            }
    }

    override suspend fun insertPlant(plant: Plant) {
        queries.insertPlant(
            id = plant.id,
            name = plant.name,
            dateObtained = plant.dateObtained,
            waterFrequencyInDays = plant.waterFrequencyInDays.toLong(),
            lastWaterAmount = plant.lastWaterAmount.toString(),
            lastDateWatered = plant.lastDateWatered,
            note = plant.note,
            isStarred = if (plant.isStarred) 1L else 0L,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deletePlant(id: Long) {
        queries.deletePlant(id)
    }
}