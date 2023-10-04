package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.features.measurements.domain.use_case.DeleteMeasurementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    measurementRepository: MeasurementRepository,
    private val deleteMeasurementUseCase: DeleteMeasurementUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(MainState(
        number = savedStateHandle["number"]!!,
        resultId = savedStateHandle["resultId"]!!
    ))
        private set

    val measurements = measurementRepository.getMeasurementsByResultId(state.resultId)

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Delete -> viewModelScope.launch {
                when (deleteMeasurementUseCase(event.id)) {
                    is ApiResponse.Success -> {
                        state = state.copy(
                            measurementResponses = state.measurementResponses.filter {
                                it.id != event.id
                            }
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}