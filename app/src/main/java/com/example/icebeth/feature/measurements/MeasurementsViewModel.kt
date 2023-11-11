package com.example.icebeth.feature.measurements

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.feature.measurements.navigation.MeasurementsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class MeasurementsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val measurementsRepository: MeasurementRepository
) : ViewModel() {
    private val measurementsArgs = MeasurementsArgs(savedStateHandle)

    var measurementsState by mutableStateOf(MeasurementsState())

    init {
        viewModelScope.launch {
            measurementsRepository
                .getMeasurementsByResultId(measurementsArgs.resultId)
                .collectLatest {
                    measurementsState = measurementsState.copy(
                        measurements = it
                    )
                }
        }
    }
}
