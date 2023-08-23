package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.measurements.data.remote.response.Measurement
import com.example.icebeth.features.measurements.domain.use_case.CreateMeasurementUseCase
import com.example.icebeth.features.measurements.domain.use_case.UpdateMeasurementUseCase
import com.example.icebeth.shared.presentation.util.UiEffect
import com.example.icebeth.shared.util.removeZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class AddMeasurementViewModel @Inject constructor(
    private val createMeasurementUseCase: CreateMeasurementUseCase,
    private val updateMeasurementUseCase: UpdateMeasurementUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AddMeasurementState(resultId = savedStateHandle["resultId"]!!))
        private set

    private val _effectFlow = MutableSharedFlow<UiEffect>()
    val effectFlow = _effectFlow.asSharedFlow()

    init {
        val savedState: String? = savedStateHandle["measurement"]
        if (savedState != null) {
            val measurement = Json.decodeFromString<Measurement>(savedState)
            state = state.copy(
                cylinderHeight = measurement.cylinderHeight.removeZero(),
                massOfSnow = measurement.massOfSnow.removeZero(),
                groundFrozzed = measurement.groundFrozzed,
                snowHeight = measurement.snowHeight.removeZero(),
                snowCrust = measurement.snowCrust,
                type = TypeMeasurement.EDIT,
                id = measurement.id,
                time = measurement.time
            )
        }
    }

    fun onEvent(event: AddMeasurementEvent) {
        when (event) {
            is AddMeasurementEvent.SetCylinderHeight -> {
                state = state.copy(cylinderHeight = event.value, cylinderHeightError = null)
            }

            is AddMeasurementEvent.SetMassOfSnow -> {
                state = state.copy(massOfSnow = event.value, massOfSnowError = null)
            }

            is AddMeasurementEvent.SetSnowHeight -> {
                state = state.copy(snowHeight = event.value, snowHeightError = null)
            }

            AddMeasurementEvent.ToggleGroundFrozzed -> {
                state = state.copy(groundFrozzed = !state.groundFrozzed)
            }

            AddMeasurementEvent.ToggleSnowCrust -> {
                state = state.copy(snowCrust = !state.snowCrust)
            }

            AddMeasurementEvent.Close -> viewModelScope.launch {
                _effectFlow.emit(UiEffect.NavigateUp)
            }

            AddMeasurementEvent.Save -> viewModelScope.launch {
                val result = when (state.type) {
                    TypeMeasurement.ADD -> createMeasurementUseCase(
                        cylinderHeight = state.cylinderHeight,
                        groundFrozzed = state.groundFrozzed,
                        massOfSnow = state.massOfSnow,
                        snowCrust = state.snowCrust,
                        snowHeight = state.snowHeight,
                        resultId = state.resultId
                    )
                    TypeMeasurement.EDIT -> updateMeasurementUseCase(
                        cylinderHeight = state.cylinderHeight,
                        groundFrozzed = state.groundFrozzed,
                        massOfSnow = state.massOfSnow,
                        snowCrust = state.snowCrust,
                        snowHeight = state.snowHeight,
                        id = state.id,
                        time = state.time
                    )
                }

                if (result.content == null) {
                    state = state.copy(
                        cylinderHeightError = result.cylinderHeightError,
                        massOfSnowError = result.massOfSnowError,
                        snowHeightError = result.snowHeightError
                    )
                    return@launch
                }

                _effectFlow.emit(UiEffect.NavigateUp)
            }
        }
    }
}