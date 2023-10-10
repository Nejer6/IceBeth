package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.common.presentation.util.UiEffect
import com.example.icebeth.common.util.removeZero
import com.example.icebeth.core.domain.CreateMeasurementUseCase
import com.example.icebeth.core.domain.UpdateMeasurementUseCase
import com.example.icebeth.features.measurements.presentation.add_measurement.navigation.AddMeasurementArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMeasurementViewModel @Inject constructor(
    private val createMeasurementUseCase: CreateMeasurementUseCase,
    private val updateMeasurementUseCase: UpdateMeasurementUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val addMeasurementArgs = AddMeasurementArgs(savedStateHandle)

    var state by mutableStateOf(AddMeasurementState(resultId = addMeasurementArgs.resultId))
        private set

    private val _effectFlow = MutableSharedFlow<UiEffect>()
    val effectFlow = _effectFlow.asSharedFlow()

    init {
        if (addMeasurementArgs.measurement != null) {
            val measurementResponse = addMeasurementArgs.measurement
            state = state.copy(
                cylinderHeight = measurementResponse.cylinderHeight.removeZero(),
                massOfSnow = measurementResponse.massOfSnow.removeZero(),
                groundFrozzed = measurementResponse.groundFrozzed,
                snowHeight = measurementResponse.snowHeight.removeZero(),
                snowCrust = measurementResponse.snowCrust,
                type = TypeMeasurement.EDIT,
                id = measurementResponse.id,
                time = measurementResponse.time
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
                        time = state.time,
                        resultId = state.resultId
                    )
                }

                if (!result.isSuccess) {
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