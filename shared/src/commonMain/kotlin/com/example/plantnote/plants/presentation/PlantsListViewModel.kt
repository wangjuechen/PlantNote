package com.example.plantnote.plants.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.plantnote.core.generateUUID
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.domain.PlantDataSource
import com.example.plantnote.plants.domain.PlantValidator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PlantsListViewModel(
    private val plantDataSource: PlantDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(PlantListState())
    val state = combine(
        _state,
        plantDataSource.getPlants(),
        plantDataSource.gtStarredPlants()
    ) { state, plants, starredPlants ->
        state.copy(
            plantList = plants,
            staredPlants = starredPlants
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PlantListState())

    var newPlant: Plant? by mutableStateOf(null)
        private set

    fun onEvent(event: PlantListEvent) {
        when (event) {
            PlantListEvent.DeletePlant -> {
                viewModelScope.launch {
                    _state.value.selectedPlant?.let { plant ->
                        _state.update {
                            it.copy(
                                isSelectedPlantSheetOpen = false
                            )
                        }
                        plantDataSource.deletePlant(plant.id)
                        delay(300L) //Animation delay
                        _state.update { it.copy(selectedPlant = null) }
                    }
                }
            }

            PlantListEvent.DismissPlant -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isSelectedPlantSheetOpen = false,
                            isAddPlantSheetOpen = false
                        )
                    }
                    delay(300L) // Animation delay. Better way is using callback of BottomSheet onHidden
                    newPlant = null
                    _state.update {
                        it.copy(
                            selectedPlant = null,
                            nameError = null,
                            dateObtainedError = null,
                            waterFrequencyError = null,
                            lastWaterDateError = null,
                            lastWaterAmountError = null
                        )
                    }
                }
            }

            PlantListEvent.OnAddNewPlantClick -> {
                _state.update {
                    it.copy(
                        isAddPlantSheetOpen = true
                    )
                }
                newPlant = Plant(
                    id = 0L,
                    name = "",
                    dateObtained = 0L,
                    isStarred = false,
                    lastDateWatered = 0L,
                    lastWaterAmount = 0.0,
                    waterFrequencyInDays = 0,
                    note = null,
                    photoBytes = null
                )
            }

            is PlantListEvent.OnEditPlant -> {
                _state.update {
                    it.copy(
                        selectedPlant = null,
                        isAddPlantSheetOpen = true,
                        isSelectedPlantSheetOpen = false
                    )
                }
                newPlant = event.plant
            }

            is PlantListEvent.OnLastDateWateredChanged -> {
                newPlant = newPlant?.copy(
                    lastDateWatered = event.value
                )
            }

            is PlantListEvent.OnDateObtainedChanged -> {
                newPlant = newPlant?.copy(
                    dateObtained = event.value
                )
            }

            is PlantListEvent.OnLastWaterAmountChanged -> {
                newPlant = newPlant?.copy(
                    lastWaterAmount = event.value
                )
            }

            is PlantListEvent.OnNameChanged -> {
                newPlant = newPlant?.copy(
                    name = event.value
                )
            }

            is PlantListEvent.OnNoteChanged -> {
                newPlant = newPlant?.copy(
                    note = event.value
                )
            }

            is PlantListEvent.OnPhotoPicked -> {
                newPlant = newPlant?.copy(
                    photoBytes = event.value
                )
            }

            is PlantListEvent.OnWaterFrequencyInDaysChanged -> {
                newPlant = newPlant?.copy(
                    waterFrequencyInDays = event.value
                )
            }

            is PlantListEvent.OnSelectPlant -> {
                _state.update {
                    it.copy(
                        isSelectedPlantSheetOpen = true,
                        selectedPlant = event.plant
                    )
                }
            }

            PlantListEvent.SavePlant -> {
                newPlant?.let { plant ->
                    val result = PlantValidator.validatePlant(plant)
                    val errors = listOfNotNull(
                        result.dateObtainedError,
                        result.waterFrequencyError,
                        result.lastWaterDateError,
                        result.nameError,
                        result.lastWaterAmountError
                    )
                    if (errors.isEmpty()) {
                        _state.update { it.copy(isAddPlantSheetOpen = false) }
                        viewModelScope.launch {
                            plantDataSource.insertPlant(plant)
                            delay(300L) // Animation delay
                            newPlant = null
                        }
                    } else {
                        _state.update {
                            it.copy(
                                nameError = result.nameError,
                                waterFrequencyError = result.waterFrequencyError,
                                lastWaterAmountError = result.lastWaterAmountError,
                                lastWaterDateError = result.lastWaterDateError,
                                dateObtainedError = result.dateObtainedError
                            )
                        }
                    }
                }
            }

            else -> Unit
        }
    }
}
