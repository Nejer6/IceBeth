package com.example.icebeth.features.measurements.presentation.results

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.features.measurements.domain.use_case.CreateResultUseCase
import com.example.icebeth.features.measurements.domain.use_case.DeleteResultUseCase
import com.example.icebeth.features.measurements.domain.use_case.GetResultsUseCase
import com.example.icebeth.shared.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val getResultsUseCase: GetResultsUseCase,
    private val deleteResultUseCase: DeleteResultUseCase,
    private val createResultUseCase: CreateResultUseCase
) : ViewModel() {

    var state by mutableStateOf(ResultsState())
        private set

    init {
        getResults()
    }

    fun onEvent(event: ResultsEvent) {
        when (event) {
            is ResultsEvent.Delete -> viewModelScope.launch {
                when (deleteResultUseCase(event.id)) {
                    is ApiResponse.Success -> {
                        state = state.copy(
                            results = state.results.filter {
                                it.id != event.id
                            }
                        )
                    }
                    else -> {}
                }
            }

            ResultsEvent.CreateResult -> viewModelScope.launch {
                when (val response = createResultUseCase()) {
                    is ApiResponse.Success -> {
                        state = state.copy(
                            results = state.results + response.body
                        )
                    }
                    else -> {
                        Log.d("myTag", "error")
                    }
                }
            }
        }
    }

    fun getResults() {
        viewModelScope.launch {
            when(val response = getResultsUseCase()) {
                is ApiResponse.Success -> {
                    state = state.copy(results = response.body)
                }
                is ApiResponse.Error.Http -> Log.d("myTag", response.status.value.toString())
                else -> {
                    Log.d("myTag", "wrong")
                }
            }
        }
    }
}