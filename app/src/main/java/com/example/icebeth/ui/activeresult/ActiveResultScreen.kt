package com.example.icebeth.ui.activeresult

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.core.domain.util.ResultError
import com.example.icebeth.ui.activeresult.components.ActiveResultMoreButton
import com.example.icebeth.ui.activeresult.components.MeasurementEditor
import com.example.icebeth.ui.components.ResultEditor

@Composable
fun ActiveResultRoute(
    navigateToMain: () -> Unit,
    viewModel: ActiveResultViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.effect.collect {
            when (it) {
                UiEffect.NavigateToMainScreen -> {
                    navigateToMain()
                }

                else -> {}
            }
        }
    }

    ActiveResultScreen(
        onSave = remember {
            {
                viewModel.save()
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
        thawedWaterLayerThicknessError = viewModel.thawedWaterLayerThicknessError,

        forciblyFinish = remember {
            {
                viewModel.forciblyFinish()
            }
        },

        expandedNumber = viewModel.expandedNumber,

        degreeOfCoverage = viewModel.degreeOfCoverage,
        snowCoverCharacter = viewModel.snowCoverCharacter,
        snowConditionDescription = viewModel.snowConditionDescription,
        onDegreeOfCoverageChange = remember {
            {
                viewModel.changeDegreeOfCoverage(it)
            }
        },
        onSnowConditionDescriptionChange = remember {
            {
                viewModel.changeSnowConditionDescription(it)
            }
        },
        onSnowCoverCharacterChange = remember {
            {
                viewModel.changeSnowCoverCharacter(it)
            }
        },
        degreeOfCoverageError = viewModel.degreeOfCoverageError,
        snowConditionDescriptionError = viewModel.snowConditionDescriptionError,
        snowCoverCharacterError = viewModel.snowCoverCharacterError,

        locationAvailable = viewModel.locationAvailable,

        measurementCount = viewModel.measurementCount,

        next = remember {
            {
                viewModel.next()
            }
        },
        previous = remember {
            {
                viewModel.previous()
            }
        },

        isEditMode = viewModel.isEditMode,
        goToEditMode = remember {
            {
                viewModel.goToEditMode()
            }
        },
        undo = remember {
            {
                viewModel.undo()
            }
        },
        editSave = remember {
            {
                viewModel.editSave()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveResultScreen(
    onSave: () -> Unit,
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

    forciblyFinish: () -> Unit,

    expandedNumber: Int?,

    degreeOfCoverage: String,
    snowCoverCharacter: SnowCoverCharacter?,
    snowConditionDescription: SnowConditionDescription?,

    onDegreeOfCoverageChange: (String) -> Unit,
    onSnowCoverCharacterChange: (SnowCoverCharacter) -> Unit,
    onSnowConditionDescriptionChange: (SnowConditionDescription) -> Unit,

    degreeOfCoverageError: ResultError?,
    snowCoverCharacterError: ResultError?,
    snowConditionDescriptionError: ResultError?,

    locationAvailable: Boolean,

    measurementCount: Int,

    next: () -> Unit,
    previous: () -> Unit,

    isEditMode: Boolean,
    goToEditMode: () -> Unit,
    undo: () -> Unit,
    editSave: () -> Unit
) {
    BackHandler(
        enabled = isEditMode
    ) {
        undo()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentMeasurementNumber > 100) {
                            "Общая характеристика"
                        } else {
                            "$currentMeasurementNumber/100"
                        }
                    )
                },
                actions = {
                    ActiveResultMoreButton(forciblyFinish = forciblyFinish)
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
                    .fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    progress = if (measurementCount < 100) {
                        measurementCount / 100f
                    } else {
                        1f
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

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

                            expandedNumber = expandedNumber,

                            isPreviousMeasurement = currentMeasurementNumber < measurementCount + 1,

                            isEditMode = isEditMode,

                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        ResultEditor(
                            degreeOfCoverage = degreeOfCoverage,
                            snowCoverCharacter = snowCoverCharacter,
                            snowConditionDescription = snowConditionDescription,
                            onDegreeOfCoverageChange = onDegreeOfCoverageChange,
                            onSnowCoverCharacterChange = onSnowCoverCharacterChange,
                            onSnowConditionDescriptionChange = onSnowConditionDescriptionChange,
                            degreeOfCoverageError = degreeOfCoverageError,
                            snowCoverCharacterError = snowCoverCharacterError,
                            snowConditionDescriptionError = snowConditionDescriptionError,

                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(MaterialTheme.spacing.medium)
            ) {
                if (!isEditMode) {
                    if (currentMeasurementNumber > 1) {
                        FloatingActionButton(
                            onClick = previous,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Назад"
                            )
                        }
                    }

                    if (currentMeasurementNumber < measurementCount + 1) {
                        FloatingActionButton(
                            onClick = goToEditMode,
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Редактировать"
                            )
                        }
                    }

                    if (
                        currentMeasurementNumber == 101 ||
                        currentMeasurementNumber == measurementCount + 1
                    ) {
                        FloatingActionButton(
                            onClick = onSave,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
                        }
                    } else {
                        FloatingActionButton(
                            onClick = next,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Дальше"
                            )
                        }
                    }
                } else {
                    FloatingActionButton(
                        onClick = editSave,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
                    }

                    FloatingActionButton(
                        onClick = undo,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(imageVector = Icons.Default.Undo, contentDescription = "Отменить")
                    }
                }
            }
        }
    }

    if (!locationAvailable) {
        Dialog(onDismissRequest = { }) {
            Text(text = "Включите GPS чтобы продолжить")
        }
    }
}
