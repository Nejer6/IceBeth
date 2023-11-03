package com.example.icebeth.feature.active_result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.feature.active_result.components.SoilSurfaceConditionDropDownMenu

@Composable
fun ActiveResultRoute(
    navigateToMain: () -> Unit,
    viewModel: ActiveResultViewModel = hiltViewModel()
) {
    ActiveResultScreen(
        onSaveMeasurement = remember {
            {
                viewModel.saveMeasurement()
            }
        },
        currentMeasurementNumber = viewModel.currentMeasurementNumber,
        snowHeight = viewModel.snowHeight,
        onChangeSnowHeight = remember {
            {
                viewModel.changeSnowHeight(it)
            }
        },
        cylinderHeight = viewModel.cylinderHeight,
        onChangeCylinderHeight = remember {
            {
                viewModel.changeCylinderHeight(it)
            }
        },
        iceCrustThickness = viewModel.iceCrustThickness,
        onChangeExpandedMeasurement = remember {
            {
                viewModel.changeExpandedMeasurement(it)
            }
        },
        isExpandedMeasurement = viewModel.isExpandedMeasurement,
        massOfSnow = viewModel.massOfSnow,
        onChangeIceCrustThickness = remember {
            {
                viewModel.changeIceCrustThickness(it)
            }
        },
        onChangeMassOfSnow = remember {
            {
                viewModel.changeMassOfSnow(it)
            }
        },
        onChangeSnowCrust = remember {
            {
                viewModel.changeSnowCrust(it)
            }
        },
        onChangeSnowLayerWaterCondition = remember {
            {
                viewModel.changeSnowLayerWaterSaturation(it)
            }
        },
        onChangeSoilSurfaceCondition = remember {
            {
                viewModel.changeSoilSurfaceCondition(it)
            }
        },
        onChangeThawedWaterLayerThickness = remember {
            {
                viewModel.changeThawedWaterLayerThickness(it)
            }
        },
        snowCrust = viewModel.snowCrust,
        snowLayerWaterCondition = viewModel.snowLayerWaterSaturation,
        soilSurfaceCondition = { viewModel.soilSurfaceCondition },
        thawedWaterLayerThickness = viewModel.thawedWaterLayerThickness,

        cylinderHeightError = viewModel.cylinderHeightError,
        iceCrustThicknessError = viewModel.iceCrustThicknessError,
        massOfSnowError = viewModel.massOfSnowError,
        snowHeightError = viewModel.snowHeightError,
        snowLayerWaterSaturationError = viewModel.snowLayerWaterSaturationError,
        soilSurfaceConditionError = viewModel.soilSurfaceConditionError,
        thawedWaterLayerThicknessError = viewModel.thawedWaterLayerThicknessError
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveResultScreen(
    onSaveMeasurement: () -> Unit,
    currentMeasurementNumber: Int,
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
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentMeasurementNumber > 100) "Общая характеристика"
                        else "$currentMeasurementNumber/100"
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Дополнительно"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                LinearProgressIndicator(
                    progress = if (currentMeasurementNumber < 100) {
                        currentMeasurementNumber / 100f
                    } else {
                        1f
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .verticalScroll(rememberScrollState())
                ) {
                    if (currentMeasurementNumber <= 100) {
                        MeasurementEditor(
                            snowHeight = snowHeight,
                            onChangeSnowHeight = onChangeSnowHeight,
                            isExpandedMeasurement,
                            onChangeExpandedMeasurement,
                            cylinderHeight,
                            onChangeCylinderHeight,
                            massOfSnow,
                            onChangeMassOfSnow,
                            soilSurfaceCondition,
                            onChangeSoilSurfaceCondition,
                            snowCrust,
                            onChangeSnowCrust,
                            iceCrustThickness,
                            onChangeIceCrustThickness,
                            snowLayerWaterCondition,
                            onChangeSnowLayerWaterCondition,
                            thawedWaterLayerThickness,
                            onChangeThawedWaterLayerThickness,

                            cylinderHeightError = cylinderHeightError,
                            iceCrustThicknessError = iceCrustThicknessError,
                            massOfSnowError = massOfSnowError,
                            snowHeightError = snowHeightError,
                            snowLayerWaterSaturationError = snowLayerWaterSaturationError,
                            soilSurfaceConditionError = soilSurfaceConditionError,
                            thawedWaterLayerThicknessError = thawedWaterLayerThicknessError,

                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                }

//                FloatingActionButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Дальше")
//                }

                FloatingActionButton(onClick = onSaveMeasurement) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
                }
            }
        }

    }
}


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

    modifier: Modifier = Modifier
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
            }
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
                }
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
                }
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
                }
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
                }
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
                }
            )

            SoilSurfaceConditionDropDownMenu(
                soilSurfaceCondition = soilSurfaceCondition(),
                onSoilSurfaceConditionChange = onChangeSoilSurfaceCondition,
                soilSurfaceConditionError = soilSurfaceConditionError
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Снежная корка", style = MaterialTheme.typography.titleMedium)
                Checkbox(checked = snowCrust, onCheckedChange = onChangeSnowCrust)
            }
        }

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