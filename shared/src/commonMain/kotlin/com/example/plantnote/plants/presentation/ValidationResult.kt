package com.example.plantnote.plants.presentation


data class ValidationResult(
    val nameError: String? = null,
    val dateObtainedError: String? = null,
    val waterFrequencyError: String? = null,
    val lastWaterDateError: String? = null,
    val lastWaterAmountError: String? = null
)