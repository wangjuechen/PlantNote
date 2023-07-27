package com.example.plantnote.plants.data

import com.example.plantnote.plants.domain.Plant
import database.PlantEntity


fun PlantEntity.toPlant(): Plant {
    return Plant(
        id = id,
        name = name,
        dateObtained = dateObtained,
        lastDateWatered = lastDateWatered,
        lastWaterAmount = lastWaterAmount.toDouble(),
        waterFrequencyInDays = waterFrequencyInDays.toInt(),
        isStarred = isStarred.toBoolean(),
        note = note,
        photoBytes = null
    )
}

private fun Long.toBoolean() = this.toInt() == 1