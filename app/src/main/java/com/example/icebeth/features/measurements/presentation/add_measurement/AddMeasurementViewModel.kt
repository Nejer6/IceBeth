package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.measurements.domain.use_case.CreateMeasurementUseCase
import com.example.icebeth.shared.presentation.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMeasurementViewModel @Inject constructor(
    private val createMeasurementUseCase: CreateMeasurementUseCase
) : ViewModel() {

    var state by mutableStateOf(AddMeasurementState())
        private set

    private val _effectFlow = MutableSharedFlow<UiEffect>()
    val effectFlow = _effectFlow.asSharedFlow()

    fun onEvent(event: AddMeasurementEvent) {
        when (event) {
            is AddMeasurementEvent.SetCylinderHeight -> {
                state = state.copy(cylinderHeight = event.value)
            }

            is AddMeasurementEvent.SetMassOfSnow -> {
                state = state.copy(massOfSnow = event.value)
            }

            is AddMeasurementEvent.SetSnowHeight -> {
                state = state.copy(snowHeight = event.value)
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
                val result = createMeasurementUseCase(
                    cylinderHeight = state.cylinderHeight,
                    groundFrozzed = state.groundFrozzed,
                    massOfSnow = state.massOfSnow,
                    snowCrust = state.snowCrust,
                    snowHeight = state.snowHeight
                )

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