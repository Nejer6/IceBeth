package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMeasurementViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(AddMeasurementState())
        private set

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
                state = state.copy(groundFrozzed = !state.snowCrust)
            }
        }
    }
}