package com.example.icebeth.ui.screens.measurementedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.ui.screens.activeresult.components.SoilSurfaceConditionDropDownMenu
import com.example.icebeth.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasurementEditScreen(
    navigateUp: () -> Unit,
    state: MeasurementEditState,
    onEvent: (MeasurementEditEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Редактирование") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Отменить")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(MeasurementEditEvent.Save) }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            OutlinedTextField(
                value = state.snowHeight,
                onValueChange = { onEvent(MeasurementEditEvent.ChangeSnowHeight(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Высота снега")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = state.snowHeightError != null,
                supportingText = {
                    if (state.snowHeightError != null) {
                        Text(
                            text = when (state.snowHeightError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotInt -> "Введите целое число"
                                else -> ""
                            }
                        )
                    }
                }
            )

            if (state.isExpandedMeasurement) {
                OutlinedTextField(
                    value = state.cylinderHeight!!,
                    onValueChange = { onEvent(MeasurementEditEvent.ChangeCylinderHeight(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Высота цилиндра")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = state.cylinderHeightError != null,
                    supportingText = {
                        if (state.cylinderHeightError != null) {
                            Text(
                                text = when (state.cylinderHeightError) {
                                    MeasurementError.Empty -> "Заполните поле"
                                    MeasurementError.NegativeNumber ->
                                        "Введите неотрицательное число"

                                    MeasurementError.NotInt -> "Введите целое число"
                                    else -> ""
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = state.massOfSnow!!,
                    onValueChange = { onEvent(MeasurementEditEvent.ChangeMassOfSnow(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Масса снега")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = state.massOfSnowError != null,
                    supportingText = {
                        if (state.massOfSnowError != null) {
                            Text(
                                text = when (state.massOfSnowError) {
                                    MeasurementError.Empty -> "Заполните поле"
                                    MeasurementError.NegativeNumber ->
                                        "Введите неотрицательное число"
                                    MeasurementError.NotDouble -> "Введите число"
                                    else -> ""
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = state.iceCrustThickness!!,
                    onValueChange = { onEvent(MeasurementEditEvent.ChangeIceCrustThickness(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Толщина ледяной корки")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = state.iceCrustThicknessError != null,
                    supportingText = {
                        if (state.iceCrustThicknessError != null) {
                            Text(
                                text = when (state.iceCrustThicknessError) {
                                    MeasurementError.Empty -> "Заполните поле"
                                    MeasurementError.NegativeNumber ->
                                        "Введите неотрицательное число"
                                    MeasurementError.NotInt -> "Введите целое число"
                                    else -> ""
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = state.snowLayerWaterCondition!!,
                    onValueChange = {
                        onEvent(
                            MeasurementEditEvent.ChangeSnowLayerWaterCondition(it)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Толщина слоя снега насыщ. водой")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = state.snowLayerWaterConditionError != null,
                    supportingText = {
                        if (state.snowLayerWaterConditionError != null) {
                            Text(
                                text = when (state.snowLayerWaterConditionError) {
                                    MeasurementError.Empty -> "Заполните поле"
                                    MeasurementError.NegativeNumber ->
                                        "Введите неотрицательное число"
                                    MeasurementError.NotInt -> "Введите целое число"
                                    else -> ""
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = state.thawedWaterLayerThickness!!,
                    onValueChange = {
                        onEvent(MeasurementEditEvent.ChangeThawedWaterLayerThickness(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Толщина слоя талой воды")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = state.thawedWaterLayerThicknessError != null,
                    supportingText = {
                        if (state.thawedWaterLayerThicknessError != null) {
                            Text(
                                text = when (state.thawedWaterLayerThicknessError) {
                                    MeasurementError.Empty -> "Заполните поле"
                                    MeasurementError.NegativeNumber ->
                                        "Введите неотрицательное число"
                                    MeasurementError.NotInt -> "Введите целое число"
                                    else -> ""
                                }
                            )
                        }
                    }
                )

                SoilSurfaceConditionDropDownMenu(
                    soilSurfaceCondition = state.soilSurfaceCondition,
                    onSoilSurfaceConditionChange = {
                        onEvent(MeasurementEditEvent.ChangeSoilSurfaceCondition(it))
                    },
                    soilSurfaceConditionError = null,
                    readOnly = false
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Снежная корка", style = MaterialTheme.typography.titleMedium)
                    Checkbox(
                        checked = state.snowCrust!!,
                        onCheckedChange = { onEvent(MeasurementEditEvent.ToggleSnowCrust) },
                        enabled = true
                    )
                }
            }
        }
    }
}
