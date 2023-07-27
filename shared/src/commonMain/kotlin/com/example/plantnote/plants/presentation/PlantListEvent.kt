package com.example.plantnote.plants.presentation

import com.example.plantnote.plants.domain.Plant


sealed interface PlantListEvent {
    object OnAddNewPlantClick : PlantListEvent
    object OnAddPhotoClicked : PlantListEvent
    object DismissPlant : PlantListEvent
    object SavePlant : PlantListEvent

    data class OnSelectPlant(val plant: Plant) : PlantListEvent
    data class OnEditPlant(val plant: Plant) : PlantListEvent
    object DeletePlant : PlantListEvent

    data class OnNameChanged(val value: String) : PlantListEvent
    data class OnDateObtainedChanged(val value: Long) : PlantListEvent
    data class OnLastDateWateredChanged(val value: Long) : PlantListEvent
    data class OnLastWaterAmountChanged(val value: Double) : PlantListEvent
    data class OnWaterFrequencyInDaysChanged(val value: Int) : PlantListEvent
    data class OnNoteChanged(val value: String) : PlantListEvent
    class OnPhotoPicked(val value: ByteArray) : PlantListEvent

}