package com.example.plantnote.plants.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.plantnote.plants.domain.Plant
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PlantsListViewModel: ViewModel() {
    fun onEvent(event: PlantListEvent) {
        TODO("Not yet implemented")
    }

    private val _state = MutableStateFlow(PlantListState(
        plantList = plants
    ))
    val state = _state.asStateFlow()

    var newPlant: Plant? by mutableStateOf(null)
        private set

}


private val plants = (1..50).map {
    Plant(
        id = it.toLong(),
        name = "Monsteria - $it",
        waterFrequencyInDays = 5,
        lastWaterAmount = 4.0,
        lastDateWatered = 234324L,
        dateObtained = 2324L,
        photoBytes = null,
        note = null
    )
}
