package com.example.plantnote.plants.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantnote.plants.domain.Plant


@Composable
fun PlantListItem(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlantPhoto(
            plant = plant,
            modifier = Modifier.size(50.dp)
        )

        Spacer(Modifier.size(16.dp))

        Text(
            text = plant.name,
            modifier = Modifier.weight(1f)
        )
    }
}
