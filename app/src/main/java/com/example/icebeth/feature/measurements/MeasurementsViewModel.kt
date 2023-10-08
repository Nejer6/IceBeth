package com.example.icebeth.feature.measurements

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.MeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeasurementsViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(
        MeasurementsState(
            number = savedStateHandle["number"]!!,
            resultId = savedStateHandle["resultId"]!!
        )
    )
        private set

    val measurements = measurementRepository.getMeasurementsByResultId(state.resultId)
        .map { list ->
            list
                .filter { !it.isDeleted }
                .sortedBy { it.time }
        }

    fun onEvent(event: MeasurementsEvent) {
        when (event) {
            is MeasurementsEvent.Delete -> viewModelScope.launch {
                measurementRepository.deleteMeasurement(event.id)
            }
        }
    }
}