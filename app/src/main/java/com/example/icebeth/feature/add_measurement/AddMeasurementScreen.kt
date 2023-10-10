package com.example.icebeth.feature.add_measurement

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.icebeth.features.measurements.domain.util.MeasurementError
import com.example.icebeth.common.presentation.theme.IceBethTheme
import com.example.icebeth.common.presentation.theme.spacing
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMeasurementScreen(
    state: AddMeasurementState,
    onEvent: (AddMeasurementEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (state.type) {
                            TypeMeasurement.ADD -> "Новый замер"
                            TypeMeasurement.EDIT -> "Изменение замера №${state.id}"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AddMeasurementEvent.Close) }) {
                        Icon(Icons.Default.Close, contentDescription = "Закрыть")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(AddMeasurementEvent.Save) }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(MaterialTheme.spacing.medium)
        ) {
            OutlinedTextField(
                value = state.massOfSnow,
                onValueChange = {
                    onEvent(AddMeasurementEvent.SetMassOfSnow(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Масса снега")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                supportingText = {
                    if (state.massOfSnowError != null) {
                        Text(
                            text = when (state.massOfSnowError) {
                                MeasurementError.Empty -> "Введите массу снега"
                                MeasurementError.NotNumber -> "Введите число"
                            },
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            OutlinedTextField(
                value = state.snowHeight,
                onValueChange = {
                    onEvent(AddMeasurementEvent.SetSnowHeight(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Высота снега")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                supportingText = {
                    if (state.snowHeightError != null) {
                        Text(
                            text = when (state.snowHeightError) {
                                MeasurementError.Empty -> "Введите высоту снега"
                                MeasurementError.NotNumber -> "Введите число"
                            },
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            OutlinedTextField(
                value = state.cylinderHeight,
                onValueChange = {
                    onEvent(AddMeasurementEvent.SetCylinderHeight(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Высота цилиндра")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                supportingText = {
                    if (state.cylinderHeightError != null) {
                        Text(
                            text = when (state.cylinderHeightError) {
                                MeasurementError.Empty -> "Введите высоту цилиндра"
                                MeasurementError.NotNumber -> "Введите число"
                            },
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Наличие снежной корки", style = MaterialTheme.typography.titleLarge)
                Checkbox(checked = state.snowCrust, onCheckedChange = {
                    onEvent(AddMeasurementEvent.ToggleSnowCrust)
                })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Факт промерзшей земли", style = MaterialTheme.typography.titleLarge)
                Checkbox(checked = state.groundFrozzed, onCheckedChange = {
                    onEvent(AddMeasurementEvent.ToggleGroundFrozzed)
                })
            }
        }
    }
}

@Preview
@Composable
fun AddMeasurementScreenPreview() {
    IceBethTheme {
        AddMeasurementScreen(state = AddMeasurementState(resultId = 1), onEvent = {
            Date().time
        })
    }
}