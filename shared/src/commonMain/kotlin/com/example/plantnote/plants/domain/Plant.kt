package com.example.plantnote.plants.domain


data class Plant(
    val id: Long,
    val name: String,
    val dateObtained: Long = 0L,
    val lastDateWatered: Long = 0L,
    val lastWaterAmount: Double = 0.0,
    val waterFrequencyInDays: String,
    val isStarred: Boolean,
    val note: String?,
    val photoBytes: ByteArray?
)
