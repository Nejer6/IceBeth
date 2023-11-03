package com.example.icebeth.feature.active_result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.data.preferences.AppPreferences
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.domain.CreateMeasurementUseCase
import com.example.icebeth.core.domain.util.MeasurementError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ActiveResultViewModel @Inject constructor(
    appPreferences: AppPreferences,
    private val resultRepository: ResultRepository,
    measurementRepository: MeasurementRepository,
    private val createMeasurementUseCase: CreateMeasurementUseCase
) : ViewModel() {
    private val resultId = appPreferences.getActiveResultId() ?: runBlocking {
        val newResultId = resultRepository.createNewResult().toInt()
        appPreferences.setActiveResultId(newResultId)
        newResultId
    }

    val measurementsCountFlow = measurementRepository.getCountOfMeasurementsByResultIdFlow(resultId)

    var currentMeasurementNumber by mutableIntStateOf(runBlocking {
        measurementsCountFlow.first() + 1
    })

    var snowHeight by mutableStateOf("")
        private set

    fun changeSnowHeight(snowHeight: String) {
        this.snowHeight = snowHeight
        snowHeightError = null
    }

    var snowHeightError by mutableStateOf<MeasurementError?>(null)
        private set

    var isExpandedMeasurement by mutableStateOf(false)
        private set

    private fun resetMeasurement() {
        snowHeight = ""
        snowHeightError = null

        isExpandedMeasurement = false

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

    fun changeExpandedMeasurement(isExpanded: Boolean) {
        isExpandedMeasurement = isExpanded
    }

    var cylinderHeight by mutableStateOf("")
        private set

    fun changeCylinderHeight(cylinderHeight: String) {
        this.cylinderHeight = cylinderHeight
        cylinderHeightError = null
    }

    var cylinderHeightError by mutableStateOf<MeasurementError?>(null)
        private set

    var massOfSnow by mutableStateOf("")
        private set

    fun changeMassOfSnow(massOfSnow: String) {
        this.massOfSnow = massOfSnow
        massOfSnowError = null
    }

    var massOfSnowError by mutableStateOf<MeasurementError?>(null)
        private set

    var soilSurfaceCondition by mutableStateOf<SoilSurfaceCondition?>(null)
        private set

    fun changeSoilSurfaceCondition(soilSurfaceCondition: SoilSurfaceCondition) {
        this.soilSurfaceCondition = soilSurfaceCondition
        soilSurfaceConditionError = null
    }

    var soilSurfaceConditionError by mutableStateOf<MeasurementError?>(null)
        private set

    var snowCrust by mutableStateOf(false)
        private set

    fun changeSnowCrust(snowCrust: Boolean) {
        this.snowCrust = snowCrust
    }

    var iceCrustThickness by mutableStateOf("")
        private set

    fun changeIceCrustThickness(iceCrustThickness: String) {
        this.iceCrustThickness = iceCrustThickness
        iceCrustThicknessError = null
    }

    var iceCrustThicknessError by mutableStateOf<MeasurementError?>(null)
        private set

    var snowLayerWaterSaturation by mutableStateOf<String>("")
        private set

    fun changeSnowLayerWaterSaturation(snowLayerWaterSaturation: String) {
        this.snowLayerWaterSaturation = snowLayerWaterSaturation
        snowLayerWaterSaturationError = null
    }

    var snowLayerWaterSaturationError by mutableStateOf<MeasurementError?>(null)
        private set

    var thawedWaterLayerThickness by mutableStateOf("")
        private set

    fun changeThawedWaterLayerThickness(thawedWaterLayerThickness: String) {
        this.thawedWaterLayerThickness = thawedWaterLayerThickness
        thawedWaterLayerThicknessError = null
    }

    var thawedWaterLayerThicknessError by mutableStateOf<MeasurementError?>(null)
        private set


    //todo add latitude and longitude
    fun saveMeasurement() {
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

    private fun nextInMeasurement() {
        resetMeasurement()

        currentMeasurementNumber += 1
    }

}