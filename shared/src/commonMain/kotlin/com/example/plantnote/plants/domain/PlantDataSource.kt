package com.example.plantnote.plants.domain

import kotlinx.coroutines.flow.Flow


interface PlantDataSource {
    fun getPlants(): Flow<List<Plant>>
    fun gtStarredPlants(): Flow<List<Plant>>
    suspend fun insertPlant(plant: Plant)
    suspend fun deletePlant(id: Long)
}