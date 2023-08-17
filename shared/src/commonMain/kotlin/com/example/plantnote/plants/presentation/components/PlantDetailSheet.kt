package com.example.plantnote.plants.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CalendarViewDay
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantnote.core.presentation.CustomBottomSheet
import com.example.plantnote.core.presentation.PlantTextField
import com.example.plantnote.plants.domain.Plant
import com.example.plantnote.plants.presentation.PlantListEvent

@Composable
fun PlantDetailSheet(
    isOpen: Boolean,
    selectedPlant: Plant?,
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
                if (selectedPlant?.photoBytes != null) {
                    PlantPhoto(
                        plant = selectedPlant,
                        iconSize = 50.dp,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(PlantListEvent.OnAddPhotoClicked)
                            }
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = selectedPlant.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
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
                EditRow(
                    onEditClick = {
                        selectedPlant?.let {
                            onEvent(PlantListEvent.OnEditPlant(it))
                        }
                    },
                    onDeleteClick = {
                        onEvent(PlantListEvent.DeletePlant)
                    }
                )
                Spacer(Modifier.height(16.dp))
                PlantInfoSection(
                    title = "Plant Name",
                    value = selectedPlant?.name ?: "No name",
                    icon = Icons.Rounded.Eco,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                PlantInfoSection(
                    title = "Birthday",
                    value = selectedPlant?.dateObtained.toString(),
                    icon = Icons.Rounded.CalendarViewDay,
                    modifier = Modifier.fillMaxWidth()
                )
                //TODO: adds more information section below as needed
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

@Composable
private fun EditRow(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        FilledTonalIconButton(
            onClick = onEditClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit Plant"
            )
        }
        FilledTonalIconButton(
            onClick = onDeleteClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Plant"
            )
        }
    }
}

@Composable
private fun PlantInfoSection(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
         Icon(
             imageVector = icon,
             contentDescription = null,
             modifier = Modifier.clip(CircleShape)
                 .background(MaterialTheme.colorScheme.secondaryContainer)
                 .padding(8.dp),
             tint = MaterialTheme.colorScheme.onSecondaryContainer
         )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp
            )
        }
    }
}