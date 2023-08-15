package com.example.plantnote.plants.data

import com.example.plantnote.core.data.ImageStorage
import com.example.plantnote.database.PlantDatabase
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.domain.PlantDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock


class SqlDelightPlantDataSource(
    db: PlantDatabase,
    private val imageStorage: ImageStorage
) : PlantDataSource {

    private val queries = db.plantQueries

    override fun getPlants(): Flow<List<Plant>> {
        return queries.getPlants()
            .asFlow()
            .mapToList()
            .map { plantEntities ->
                supervisorScope {
                    plantEntities
                        .map {
                            async { it.toPlant(imageStorage) }
                        }.map { it.await() }
                }
            }
    }

    override fun gtStarredPlants(): Flow<List<Plant>> {
        return queries.getStarredPlants().asFlow().mapToList()
            .map {
                it.map { plantEntity -> plantEntity.toPlant(imageStorage) }
            }
    }

    override suspend fun insertPlant(plant: Plant) {
        val imagePath = plant.photoBytes?.let {
            imageStorage.saveImage(it)
        }
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
            imagePath = imagePath
        )
    }

    override suspend fun deletePlant(id: Long) {
        val entity = queries.getPlantById(id).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        queries.deletePlant(id)
    }
}