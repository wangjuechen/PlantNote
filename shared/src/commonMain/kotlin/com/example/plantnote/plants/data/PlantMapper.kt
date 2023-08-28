package com.example.plantnote.plants.data

import com.example.plantnote.core.data.ImageStorage
import com.example.plantnote.plants.domain.Plant
import database.PlantEntity


suspend fun PlantEntity.toPlant(imageStorage: ImageStorage): Plant {
    return Plant(
        id = id,
        name = name,
        dateObtained = dateObtained,
        lastDateWatered = lastDateWatered,
        lastWaterAmount = lastWaterAmount.toDouble(),
        waterFrequencyInDays = waterFrequencyInDays.toString(),
        isStarred = isStarred.toBoolean(),
        note = note,
        photoBytes = imagePath?.let { imageStorage.getImage(it)  }
    )
}

private fun Long.toBoolean() = this.toInt() == 1