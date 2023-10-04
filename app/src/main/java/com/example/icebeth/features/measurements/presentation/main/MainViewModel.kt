package com.example.icebeth.features.measurements.presentation.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.measurements.domain.use_case.DeleteMeasurementUseCase
import com.example.icebeth.features.measurements.domain.use_case.GetMeasurementsUseCase
import com.example.icebeth.common.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMeasurementsUseCase: GetMeasurementsUseCase,
    private val deleteMeasurementUseCase: DeleteMeasurementUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(MainState(
        number = savedStateHandle["number"]!!,
        resultId = savedStateHandle["resultId"]!!
    ))
        private set

    init {
        getMeasurements()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Delete -> viewModelScope.launch {
                when (deleteMeasurementUseCase(event.id)) {
                    is ApiResponse.Success -> {
                        state = state.copy(
                            measurements = state.measurements.filter {
                                it.id != event.id
                            }
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    fun getMeasurements() {
        viewModelScope.launch {
            when(val response = getMeasurementsUseCase(state.resultId)) {
                is ApiResponse.Success -> {
                    state = state.copy(measurements = response.body)
                }
                is ApiResponse.Error.Http -> Log.d("myTag", response.status.value.toString())
                else -> {
                    Log.d("myTag", "wrong")
                }
            }
        }
    }
}