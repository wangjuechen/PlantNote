package com.example.plantnote.plants.domain


data class Plant(
    val id: Long,
    val name: String,
    val dateObtained: Long,
    val lastDateWatered: Long,
    val lastWaterAmount: Double,
    val waterFrequencyInDays: Int,
    val note: String?,
    val photoBytes: ByteArray?
)
