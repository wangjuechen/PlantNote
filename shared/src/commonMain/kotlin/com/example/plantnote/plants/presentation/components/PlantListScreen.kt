package com.example.plantnote.plants.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.presentation.PlantListEvent
import com.example.plantnote.plants.presentation.PlantListState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantListScreen(
    state: PlantListState,
    newPlant: Plant?,
    onEvent: (PlantListEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(PlantListEvent.OnAddNewPlantClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add Plant"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "My plants (${state.plantList.size})",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.plantList) {plant ->
                PlantListItem(
                    plant = plant,
                    modifier = Modifier.fillMaxWidth().clickable {
                        onEvent(PlantListEvent.OnSelectPlant(plant))
                    }.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
