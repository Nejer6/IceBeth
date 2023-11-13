package com.example.icebeth.ui.screens.activeresult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.data.location.LocationClient
import com.example.icebeth.core.data.preferences.AppPreferences
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.domain.CreateMeasurementUseCase
import com.example.icebeth.core.domain.FinishResultUseCase
import com.example.icebeth.core.domain.UpdateMeasurementUseCase
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.core.domain.util.ResultError
import com.example.icebeth.core.model.MeasurementCreateResult
import com.example.icebeth.ui.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ActiveResultViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val resultRepository: ResultRepository,
    measurementRepository: MeasurementRepository,
    private val createMeasurementUseCase: CreateMeasurementUseCase,
    private val finishResultUseCase: FinishResultUseCase,
    private val updateMeasurementUseCase: UpdateMeasurementUseCase,
    locationClient: LocationClient
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

    private var measurementsList = emptyList<MeasurementEntity>()

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

    var currentMeasurementNumber by mutableIntStateOf(
        runBlocking {
            measurementsCountFlow.first() + 1
        }
    )
        private set

    var measurementCount by mutableIntStateOf(
        runBlocking {
            measurementsCountFlow.first()
        }
    )
        private set

    private var measurementId = 0

    private val locationFlow = locationClient.getLocationUpdates(5000)

    private var latestLatitude = 0.0
    private var latestLongitude = 0.0

    private var latitude = 0.0
    private var longitude = 0.0

    var locationAvailable by mutableStateOf(locationClient.isLocationAvailable())
        private set

    private var lastMeasurementState = LastMeasurementState()

    var isEditMode by mutableStateOf(false)
        private set

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

    init {
        viewModelScope.launch {
            locationFlow.collectLatest {
                if (it == null) {
                    locationAvailable = false
                } else {
                    locationAvailable = true
                    latestLatitude = it.latitude
                    latestLongitude = it.longitude
                }
            }
        }

        viewModelScope.launch {
            measurementsCountFlow.collectLatest {
                measurementCount = it
            }
        }

        viewModelScope.launch {
            measurementsFlow.collectLatest {
                measurementsList = it
            }
        }
    }

    fun forciblyFinish() {
        viewModelScope.launch {
            resultRepository.deleteResultById(resultId)
            finish()
        }
    }

    private fun finish() {
        viewModelScope.launch {
            appPreferences.setActiveResultId(null)
            _effect.send(UiEffect.NavigateToMainScreen)
        }
    }

    fun changeSnowHeight(snowHeight: String) {
        this.snowHeight = snowHeight
        snowHeightError = null
        latitude = latestLatitude
        longitude = latestLongitude
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

    private fun saveMeasurement() {
        viewModelScope.launch {
            val measurementCreateResult = createMeasurementUseCase(
                resultId = resultId,
                latitude = latitude,
                longitude = longitude,
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
                updateExpandedNumber()

                lastMeasurementState = LastMeasurementState()

                next()
            } else {
                handleMeasurementErrors(measurementCreateResult)
            }
        }
    }

    private fun updateExpandedNumber() {
        if (expandedNumber == null) {
            if (isExpandedMeasurement) {
                expandedNumber = currentMeasurementNumber % 10
            } else if (currentMeasurementNumber == 9) {
                expandedNumber = 0
            }
        }
    }

    private fun handleMeasurementErrors(measurementCreateResult: MeasurementCreateResult) {
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

    private fun saveResult() {
        viewModelScope.launch {
            val updateResult = finishResultUseCase(
                resultId = resultId,
                degreeOfCoverage = degreeOfCoverage,
                snowCoverCharacter = snowCoverCharacter,
                snowConditionDescription = snowConditionDescription
            )

            if (updateResult.isSuccess) {
                finish()
            } else {
                degreeOfCoverageError = updateResult.degreeOfCoverageError
                snowConditionDescriptionError = updateResult.snowConditionDescriptionError
                snowCoverCharacterError = updateResult.snowCoverCharacterError
            }
        }
    }

    fun save() {
        if (measurementCount == 100) {
            saveResult()
        } else {
            saveMeasurement()
        }
    }

    private fun resetMeasurement() {
        snowHeight = lastMeasurementState.snowHeight

        cylinderHeight = lastMeasurementState.cylinderHeight
        massOfSnow = lastMeasurementState.massOfSnow
        snowCrust = lastMeasurementState.snowCrust
        iceCrustThickness = lastMeasurementState.iceCrustThickness
        snowLayerWaterSaturation = lastMeasurementState.snowLayerWaterSaturation
        thawedWaterLayerThickness = lastMeasurementState.thawedWaterLayerThickness
        soilSurfaceCondition = lastMeasurementState.soilSurfaceCondition

        resetMeasurementErrors()

        if (lastMeasurementState.isExpandedMeasurement != null) {
            isExpandedMeasurement = lastMeasurementState.isExpandedMeasurement!!
        } else {
            handleIsExpandedMeasurement()
        }
    }

    private fun resetMeasurementErrors() {
        snowHeightError = null
        cylinderHeightError = null
        massOfSnowError = null
        iceCrustThicknessError = null
        snowLayerWaterSaturationError = null
        thawedWaterLayerThicknessError = null
        soilSurfaceConditionError = null
    }

    private fun handleIsExpandedMeasurement() {
        isExpandedMeasurement = if (expandedNumber == null) {
            currentMeasurementNumber == 10
        } else {
            currentMeasurementNumber % 10 == expandedNumber
        }
    }

    fun next() {
        currentMeasurementNumber += 1

        if (currentMeasurementNumber == 101) return

        if (currentMeasurementNumber <= measurementCount) {
            loadMeasurement()
        } else {
            resetMeasurement()
        }
    }

    fun previous() {
        if (currentMeasurementNumber < 101 && currentMeasurementNumber == measurementCount + 1) {
            lastMeasurementState = LastMeasurementState(
                snowHeight,
                cylinderHeight,
                massOfSnow,
                iceCrustThickness,
                snowLayerWaterSaturation,
                thawedWaterLayerThickness,
                soilSurfaceCondition,
                snowCrust,
                isExpandedMeasurement
            )
        }

        currentMeasurementNumber -= 1

        loadMeasurement()
    }

    private fun loadMeasurement() {
        val measurement = measurementsList[currentMeasurementNumber - 1]

        resetMeasurementErrors()

        measurementId = measurement.id

        isExpandedMeasurement = measurement.cylinderHeight != null

        snowHeight = measurement.snowHeight.toString()

        cylinderHeight = measurement.cylinderHeight?.toString() ?: ""
        massOfSnow = measurement.massOfSnow?.toString() ?: ""
        snowCrust = measurement.snowCrust ?: false
        iceCrustThickness = measurement.iceCrustThickness?.toString() ?: ""
        snowLayerWaterSaturation = measurement.snowLayerWaterSaturation?.toString() ?: ""
        thawedWaterLayerThickness = measurement.thawedWaterLayerThickness?.toString() ?: ""
        soilSurfaceCondition = measurement.soilSurfaceCondition
    }

    fun goToEditMode() {
        isEditMode = true
    }

    private fun exitEditMode() {
        isEditMode = false
    }

    fun undo() {
        exitEditMode()

        loadMeasurement()
    }

    fun editSave() {
        viewModelScope.launch {
            val measurementCreateResult = updateMeasurementUseCase(
                measurementId = measurementId,
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
                exitEditMode()
            } else {
                handleMeasurementErrors(measurementCreateResult)
            }
        }
    }
}
