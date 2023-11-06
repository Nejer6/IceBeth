package com.example.icebeth.feature.active_result.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasurementEditor(
    snowHeight: String,
    onChangeSnowHeight: (String) -> Unit,
    isExpandedMeasurement: Boolean,
    onChangeExpandedMeasurement: (Boolean) -> Unit,
    cylinderHeight: String,
    onChangeCylinderHeight: (String) -> Unit,
    massOfSnow: String,
    onChangeMassOfSnow: (String) -> Unit,
    soilSurfaceCondition: () -> SoilSurfaceCondition?,
    onChangeSoilSurfaceCondition: (SoilSurfaceCondition) -> Unit,
    snowCrust: Boolean,
    onChangeSnowCrust: (Boolean) -> Unit,
    iceCrustThickness: String,
    onChangeIceCrustThickness: (String) -> Unit,
    snowLayerWaterCondition: String,
    onChangeSnowLayerWaterCondition: (String) -> Unit,
    thawedWaterLayerThickness: String,
    onChangeThawedWaterLayerThickness: (String) -> Unit,

    snowHeightError: MeasurementError?,
    cylinderHeightError: MeasurementError?,
    massOfSnowError: MeasurementError?,
    iceCrustThicknessError: MeasurementError?,
    snowLayerWaterSaturationError: MeasurementError?,
    thawedWaterLayerThicknessError: MeasurementError?,
    soilSurfaceConditionError: MeasurementError?,

    expandedNumber: Int?,

    isPreviousMeasurement: Boolean,

    isEditMode: Boolean,

    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = snowHeight,
            onValueChange = onChangeSnowHeight,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Высота снега")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            isError = snowHeightError != null,
            supportingText = {
                if (snowHeightError != null) {
                    Text(
                        text = when (snowHeightError) {
                            MeasurementError.Empty -> "Заполните поле"
                            MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                            MeasurementError.NotInt -> "Введите целое число"
                            else -> ""
                        }
                    )
                }
            },
            readOnly = isPreviousMeasurement && !isEditMode
        )

        if (isExpandedMeasurement) {
            OutlinedTextField(
                value = cylinderHeight,
                onValueChange = onChangeCylinderHeight,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Высота цилиндра")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = cylinderHeightError != null,
                supportingText = {
                    if (cylinderHeightError != null) {
                        Text(
                            text = when (cylinderHeightError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotInt -> "Введите целое число"
                                else -> ""
                            }
                        )
                    }
                },
                readOnly = isPreviousMeasurement && !isEditMode
            )

            OutlinedTextField(
                value = massOfSnow,
                onValueChange = onChangeMassOfSnow,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Масса снега")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = massOfSnowError != null,
                supportingText = {
                    if (massOfSnowError != null) {
                        Text(
                            text = when (massOfSnowError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotDouble -> "Введите число"
                                else -> ""
                            }
                        )
                    }
                },
                readOnly = isPreviousMeasurement && !isEditMode
            )

            OutlinedTextField(
                value = iceCrustThickness,
                onValueChange = onChangeIceCrustThickness,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Толщина ледяной корки")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = iceCrustThicknessError != null,
                supportingText = {
                    if (iceCrustThicknessError != null) {
                        Text(
                            text = when (iceCrustThicknessError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotInt -> "Введите целое число"
                                else -> ""
                            }
                        )
                    }
                },
                readOnly = isPreviousMeasurement && !isEditMode
            )

            OutlinedTextField(
                value = snowLayerWaterCondition,
                onValueChange = onChangeSnowLayerWaterCondition,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Толщина слоя снега насыщ. водой")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = snowLayerWaterSaturationError != null,
                supportingText = {
                    if (snowLayerWaterSaturationError != null) {
                        Text(
                            text = when (snowLayerWaterSaturationError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotInt -> "Введите целое число"
                                else -> ""
                            }
                        )
                    }
                },
                readOnly = isPreviousMeasurement && !isEditMode
            )

            OutlinedTextField(
                value = thawedWaterLayerThickness,
                onValueChange = onChangeThawedWaterLayerThickness,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Толщина слоя талой воды")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = thawedWaterLayerThicknessError != null,
                supportingText = {
                    if (thawedWaterLayerThicknessError != null) {
                        Text(
                            text = when (thawedWaterLayerThicknessError) {
                                MeasurementError.Empty -> "Заполните поле"
                                MeasurementError.NegativeNumber -> "Введите неотрицательное число"
                                MeasurementError.NotInt -> "Введите целое число"
                                else -> ""
                            }
                        )
                    }
                },
                readOnly = isPreviousMeasurement && !isEditMode
            )

            SoilSurfaceConditionDropDownMenu(
                soilSurfaceCondition = soilSurfaceCondition(),
                onSoilSurfaceConditionChange = onChangeSoilSurfaceCondition,
                soilSurfaceConditionError = soilSurfaceConditionError,
                readOnly = isPreviousMeasurement && !isEditMode
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Снежная корка", style = MaterialTheme.typography.titleMedium)
                Checkbox(
                    checked = snowCrust,
                    onCheckedChange = onChangeSnowCrust,
                    enabled = !isPreviousMeasurement || isEditMode
                )
            }
        }

        if (!isPreviousMeasurement && expandedNumber == null) {
            Button(onClick = { onChangeExpandedMeasurement(!isExpandedMeasurement) }) {
                Text(
                    text = when (isExpandedMeasurement) {
                        true -> "Убрать"
                        false -> "Расширить"
                    }
                )
            }
        }
    }
}