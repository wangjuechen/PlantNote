package com.example.plantnote.plants.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.plantnote.core.presentation.CustomBottomSheet
import com.example.plantnote.core.presentation.PlantsTheme
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.presentation.PlantListEvent
import com.example.plantnote.plants.presentation.PlantListState
import epicarchitect.calendar.compose.basis.BasisEpicCalendar
import epicarchitect.calendar.compose.basis.config.rememberBasisEpicCalendarConfig
import epicarchitect.calendar.compose.basis.config.rememberMutableBasisEpicCalendarConfig
import epicarchitect.calendar.compose.basis.state.rememberBasisEpicCalendarState
import epicarchitect.calendar.compose.basis.state.rememberMutableBasisEpicCalendarState
import epicarchitect.calendar.compose.datepicker.state.EpicDatePickerState
import epicarchitect.calendar.compose.datepicker.state.rememberEpicDatePickerState
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddPlantSheet(
    state: PlantListState,
    newPlant: Plant?,
    isOpen: Boolean,
    onEvent: (PlantListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    CustomBottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                if (newPlant?.photoBytes != null) {
                    PlantPhoto(
                        plant = newPlant,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(PlantListEvent.OnAddPhotoClicked)
                            }
                    )
                } else {
                    Box(
                        modifier = Modifier.size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colorScheme.background)
                            .clickable {
                                onEvent(PlantListEvent.OnAddPhotoClicked)
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                shape = RoundedCornerShape(40)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add new photo",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(40.dp).align(Alignment.Center)
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                PlantTextField(
                    value = newPlant?.name ?: "",
                    placeHolder = "Plant name",
                    error = state.nameError,
                    onValueChanged = {
                        onEvent(PlantListEvent.OnNameChanged(it))
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Spacer(Modifier.height(16.dp))
                PlantTextField(
                    value = newPlant?.dateObtained.toString(), //TODO: leave this for now, needs a long->date conversion method to convert
                    placeHolder = "When you got this plant",
                    error = state.dateObtainedError,
                    onValueChanged = {
                        //TODO: leave this for now, needs a date selection dialog here
//                        onEvent(PlantListEvent.OnDateObtainedChanged(it.toString()))
                    },
                    modifier = Modifier.fillMaxSize()
                )
                Spacer(Modifier.height(16.dp))
                PlantTextField(
                    value = newPlant?.lastDateWatered.toString(), //TODO: leave this for now, needs a long->date conversion method to convert
                    placeHolder = "When you watered last time",
                    error = state.lastWaterDateError,
                    onValueChanged = {
                        //TODO: leave this for now, needs a date selection Composable dialog here
//                        onEvent(PlantListEvent.OnLastDateWateredChanged(it))
                    },
                    modifier = Modifier.fillMaxSize()
                )
//                Spacer(Modifier.height(16.dp))
//                PlantTextField(
//                    value = newPlant?.lastWaterAmount.toString(), //TODO: leave this for now, needs a number field conversion method to convert
//                    placeHolder = "How much water you gave last time",
//                    error = state.lastWaterAmountError,
//                    onValueChanged = {
//                        //TODO: leave this for now, needs a number selection Composable dialog here
////                        onEvent(PlantListEvent.OnLastWaterAmountChanged(it))
//                    },
//                    modifier = Modifier.fillMaxSize()
//                )
                Spacer(Modifier.height(16.dp))
                PlantTextField(
                    value = newPlant?.waterFrequencyInDays ?: "",
                    placeHolder = "How often you water in days",
                    error = state.waterFrequencyError,
                    onValueChanged = {
                        onEvent(PlantListEvent.OnWaterFrequencyInDaysChanged(it))
                    },
                    modifier = Modifier.fillMaxSize(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(Modifier.height(16.dp))
                PlantTextField(
                    value = newPlant?.note ?: "",
                    placeHolder = "Note",
                    error = null,
                    onValueChanged = {
                        onEvent(PlantListEvent.OnNoteChanged(it))
                    },
                    modifier = Modifier.fillMaxSize()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onEvent(PlantListEvent.SavePlant)
                    }
                ) {
                    Text(
                        text = "Save"
                    )
                }
                //TODO: adds a star at the right top corner to show if this plant is starred
            }
            IconButton(
                onClick = {
                    onEvent(PlantListEvent.DismissPlant)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantTextField(
    value: String,
    placeHolder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeHolder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = keyboardOptions
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun CalendarDatePicker(
    visible: Boolean,
    onDateSelected: (LocalDate) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it }
        )
    ) {
        val calendarState = rememberMutableBasisEpicCalendarState(
            config = rememberMutableBasisEpicCalendarConfig(
                rowsSpacerHeight = 4.dp,
                dayOfWeekViewHeight = 40.dp,
                dayOfMonthViewHeight = 40.dp,
                columnWidth = 40.dp,
                dayOfWeekViewShape = RoundedCornerShape(16.dp),
                dayOfMonthViewShape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(100.dp),
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                displayDaysOfAdjacentMonths = true,
                displayDaysOfWeek = true
            )
        )

        BasisEpicCalendar(
            state = calendarState,
            onDayOfMonthClick = onDateSelected
        )
    }
}
