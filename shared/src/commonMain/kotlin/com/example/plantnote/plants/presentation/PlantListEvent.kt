package com.example.plantnote.plants.presentation

import com.example.plantnote.plants.domain.Plant


sealed interface PlantListEvent {
    data object OnAddNewPlantClick : PlantListEvent
    data object OnAddPhotoClicked : PlantListEvent
    data object DismissPlant : PlantListEvent
    data object SavePlant : PlantListEvent

    data class OnSelectPlant(val plant: Plant) : PlantListEvent
    data class OnEditPlant(val plant: Plant) : PlantListEvent
    data object DeletePlant : PlantListEvent

    data class OnNameChanged(val value: String) : PlantListEvent
    data class OnDateObtainedChanged(val value: Long) : PlantListEvent
    data class OnLastDateWateredChanged(val value: Long) : PlantListEvent
    data class OnLastWaterAmountChanged(val value: Double) : PlantListEvent
    data class OnWaterFrequencyInDaysChanged(val value: String) : PlantListEvent
    data class OnNoteChanged(val value: String) : PlantListEvent
    class OnPhotoPicked(val value: ByteArray) : PlantListEvent

}