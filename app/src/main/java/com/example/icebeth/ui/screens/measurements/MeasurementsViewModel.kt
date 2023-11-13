package com.example.icebeth.ui.screens.measurements

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.ui.screens.measurements.navigation.MeasurementsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MeasurementsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val measurementsRepository: MeasurementRepository
) : ViewModel() {
    private val measurementsArgs = MeasurementsArgs(savedStateHandle)

    var state by mutableStateOf(MeasurementsState())

    private val _effect = Channel<MeasurementsEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            measurementsRepository
                .getMeasurementsByResultId(measurementsArgs.resultId)
                .collectLatest {
                    state = state.copy(
                        measurements = it
                    )
                }
        }
    }

    fun onEvent(event: MeasurementsEvent) {
        when (event) {
            MeasurementsEvent.ToggleEditMode -> {
                state = state.copy(
                    isEditMode = !state.isEditMode
                )

                if (state.isEditMode) {
                    viewModelScope.launch {
                        _effect.send(MeasurementsEffect.InEditMode)
                    }
                }
            }
        }
    }
}
