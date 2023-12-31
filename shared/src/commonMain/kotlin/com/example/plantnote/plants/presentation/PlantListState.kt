package com.example.plantnote.plants.presentation

import com.example.plantnote.plants.domain.Plant


data class PlantListState(
  val plantList: List<Plant> = emptyList(),
  val staredPlants: List<Plant> = emptyList(),
  val selectedPlant: Plant? = null,
  val nextPlantDueToWater: Plant? = null,
  val isAddPlantSheetOpen: Boolean = false,
  val isSelectedPlantSheetOpen: Boolean = false,
  val nameError: String? = null,
  val dateObtainedError: String? = null,
  val waterFrequencyError: String? = null,
  val lastWaterDateError: String? = null,
  val lastWaterAmountError: String? = null
)