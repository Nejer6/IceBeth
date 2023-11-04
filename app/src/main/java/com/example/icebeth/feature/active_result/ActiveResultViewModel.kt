package com.example.icebeth.feature.active_result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.data.preferences.AppPreferences
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.domain.CreateMeasurementUseCase
import com.example.icebeth.core.domain.UpdateResultUseCase
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.core.domain.util.ResultError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ActiveResultViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val resultRepository: ResultRepository,
    measurementRepository: MeasurementRepository,
    private val createMeasurementUseCase: CreateMeasurementUseCase,
    private val updateResultUseCase: UpdateResultUseCase
) : ViewModel() {
    private val resultId = appPreferences.getActiveResultId() ?: runBlocking {
        val newResultId = resultRepository.createNewResult().toInt()
        appPreferences.setActiveResultId(newResultId)
        newResultId
    }

    private val measurementsFlow = measurementRepository
        .getMeasurementsByResultId(resultId)
        .map { list ->
            list.sortedBy { it.time }
        }

    var expandedNumber by mutableStateOf(
        runBlocking {
            val index = measurementsFlow.first().indexOfFirst { it.cylinderHeight != null }
            if (index == -1) {
                null
            } else {
                (index + 1) % 10
            }
        }
    )

    private val _effect = Channel<UiEffect>()
    val effect = _effect.receiveAsFlow()

    private val measurementsCountFlow =
        measurementRepository.getCountOfMeasurementsByResultIdFlow(resultId)

    var currentMeasurementNumber by mutableIntStateOf(runBlocking {
        measurementsCountFlow.first() + 1
    })

    var snowHeight by mutableStateOf("")
        private set

    var snowHeightError by mutableStateOf<MeasurementError?>(null)
        private set

    var isExpandedMeasurement by mutableStateOf(
        expandedNumber != null && currentMeasurementNumber % 10 == expandedNumber
    )
        private set

    var cylinderHeight by mutableStateOf("")
        private set

    var cylinderHeightError by mutableStateOf<MeasurementError?>(null)
        private set

    var massOfSnow by mutableStateOf("")
        private set

    var massOfSnowError by mutableStateOf<MeasurementError?>(null)
        private set

    var soilSurfaceCondition by mutableStateOf<SoilSurfaceCondition?>(null)
        private set

    var soilSurfaceConditionError by mutableStateOf<MeasurementError?>(null)
        private set

    var snowCrust by mutableStateOf(false)
        private set

    var iceCrustThicknessError by mutableStateOf<MeasurementError?>(null)
        private set

    var snowLayerWaterSaturation by mutableStateOf("")
        private set

    var snowLayerWaterSaturationError by mutableStateOf<MeasurementError?>(null)
        private set

    var thawedWaterLayerThickness by mutableStateOf("")
        private set

    var thawedWaterLayerThicknessError by mutableStateOf<MeasurementError?>(null)
        private set

    var iceCrustThickness by mutableStateOf("")
        private set

    var degreeOfCoverage by mutableStateOf("")
        private set

    var snowCoverCharacter by mutableStateOf<SnowCoverCharacter?>(null)
        private set

    var snowConditionDescription by mutableStateOf<SnowConditionDescription?>(null)
        private set

    var degreeOfCoverageError by mutableStateOf<ResultError?>(null)
        private set

    var snowCoverCharacterError by mutableStateOf<ResultError?>(null)
        private set

    var snowConditionDescriptionError by mutableStateOf<ResultError?>(null)
        private set

    fun forciblyFinish() {
        viewModelScope.launch {
            resultRepository.deleteResultById(resultId)
            appPreferences.setActiveResultId(null)
            _effect.send(UiEffect.NavigateToMainScreen)
        }
    }

    fun changeSnowHeight(snowHeight: String) {
        this.snowHeight = snowHeight
        snowHeightError = null
    }

    fun changeExpandedMeasurement(isExpanded: Boolean) {
        isExpandedMeasurement = isExpanded
    }

    fun changeCylinderHeight(cylinderHeight: String) {
        this.cylinderHeight = cylinderHeight
        cylinderHeightError = null
    }

    fun changeMassOfSnow(massOfSnow: String) {
        this.massOfSnow = massOfSnow
        massOfSnowError = null
    }

    fun changeSoilSurfaceCondition(soilSurfaceCondition: SoilSurfaceCondition) {
        this.soilSurfaceCondition = soilSurfaceCondition
        soilSurfaceConditionError = null
    }

    fun changeSnowCrust(snowCrust: Boolean) {
        this.snowCrust = snowCrust
    }

    fun changeIceCrustThickness(iceCrustThickness: String) {
        this.iceCrustThickness = iceCrustThickness
        iceCrustThicknessError = null
    }

    fun changeSnowLayerWaterSaturation(snowLayerWaterSaturation: String) {
        this.snowLayerWaterSaturation = snowLayerWaterSaturation
        snowLayerWaterSaturationError = null
    }

    fun changeThawedWaterLayerThickness(thawedWaterLayerThickness: String) {
        this.thawedWaterLayerThickness = thawedWaterLayerThickness
        thawedWaterLayerThicknessError = null
    }

    fun changeDegreeOfCoverage(degreeOfCoverage: String) {
        this.degreeOfCoverage = degreeOfCoverage
        degreeOfCoverageError = null
    }

    fun changeSnowCoverCharacter(snowCoverCharacter: SnowCoverCharacter) {
        this.snowCoverCharacter = snowCoverCharacter
        snowCoverCharacterError = null
    }

    fun changeSnowConditionDescription(snowConditionDescription: SnowConditionDescription) {
        this.snowConditionDescription = snowConditionDescription
        snowConditionDescriptionError = null
    }

    //todo add latitude and longitude
    private fun saveMeasurement() {
        viewModelScope.launch {
            val measurementCreateResult = createMeasurementUseCase(
                resultId = resultId,
                latitude = 0.0,
                longitude = 0.0,
                cylinderHeight = cylinderHeight,
                iceCrustThickness = iceCrustThickness,
                massOfSnow = massOfSnow,
                snowCrust = snowCrust,
                snowHeight = snowHeight,
                snowLayerWaterSaturation = snowLayerWaterSaturation,
                soilSurfaceCondition = soilSurfaceCondition,
                thawedWaterLayerThickness = thawedWaterLayerThickness,
                isExpanded = isExpandedMeasurement
            )

            if (measurementCreateResult.isSuccess) {
                if (expandedNumber == null) {
                    if (isExpandedMeasurement) {
                        expandedNumber = currentMeasurementNumber % 10
                    } else if (currentMeasurementNumber == 9) {
                        expandedNumber = 0
                    }
                }


                nextInMeasurement()
            } else {
                cylinderHeightError = measurementCreateResult.cylinderHeightError
                iceCrustThicknessError = measurementCreateResult.iceCrustThicknessError
                massOfSnowError = measurementCreateResult.massOfSnowError
                snowHeightError = measurementCreateResult.snowHeightError
                snowLayerWaterSaturationError =
                    measurementCreateResult.snowLayerWaterSaturationError
                soilSurfaceConditionError = measurementCreateResult.soilSurfaceConditionError
                thawedWaterLayerThicknessError =
                    measurementCreateResult.thawedWaterLayerThicknessError
            }
        }
    }

    private fun saveResult() {
        viewModelScope.launch {
            val updateResult = updateResultUseCase(
                resultId = resultId,
                degreeOfCoverage = degreeOfCoverage,
                snowCoverCharacter = snowCoverCharacter,
                snowConditionDescription = snowConditionDescription
            )

            if (updateResult.isSuccess) {

            } else {
                degreeOfCoverageError = updateResult.degreeOfCoverageError
                snowConditionDescriptionError = updateResult.snowConditionDescriptionError
                snowCoverCharacterError = updateResult.snowCoverCharacterError
            }
        }
    }

    fun save() {
        if (currentMeasurementNumber > 100) {
            saveResult()
        } else {
            saveMeasurement()
        }
    }

    private fun resetMeasurement() {
        snowHeight = ""
        snowHeightError = null

        isExpandedMeasurement = if (expandedNumber == null) {
            currentMeasurementNumber == 10
        } else {
            currentMeasurementNumber % 10 == expandedNumber
        }

        cylinderHeight = ""
        cylinderHeightError = null

        massOfSnow = ""
        massOfSnowError = null

        snowCrust = false

        iceCrustThickness = ""
        iceCrustThicknessError = null

        snowLayerWaterSaturation = ""
        snowLayerWaterSaturationError = null

        thawedWaterLayerThickness = ""
        thawedWaterLayerThicknessError = null

        soilSurfaceCondition = null
        soilSurfaceConditionError = null
    }

    private fun nextInMeasurement() {
        currentMeasurementNumber += 1

        resetMeasurement()
    }
}