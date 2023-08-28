package com.example.plantnote.plants.domain

import com.example.plantnote.plants.presentation.ValidationResult


object PlantValidator {

    fun validatePlant(plant: Plant): ValidationResult {
        var validationResult = ValidationResult()

        if (plant.name.isBlank()) {
            validationResult = validationResult.copy(nameError = "The name of plant can't be empty.")
        }

        if (plant.lastDateWatered == 0L) {
            validationResult = validationResult.copy(lastWaterDateError = "Last date you watered the plant can't be empty.")
        }

        if (plant.dateObtained == 0L) {
            validationResult = validationResult.copy(dateObtainedError = "The date you got the plant can't be empty.")
        }

        if (plant.lastWaterAmount == 0.0) {
            validationResult = validationResult.copy(lastWaterAmountError = "The amount of water you gave to plant can't be empty.")
        }

        if (plant.waterFrequencyInDays.isBlank()) {
            validationResult = validationResult.copy(waterFrequencyError = "The frequency for watering the plant can't be empty.")
        }

        return validationResult
    }
}
