package com.example.icebeth.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val resultRepository: ResultRepository,
    private val measurementRepository: MeasurementRepository
) : ViewModel() {

    val resultWithMeasurements = resultRepository.getActiveResultWithMeasurements().map {
        it?.copy(
            result = it.result,
            measurements = it.measurements
                .filter { measurement -> !measurement.isDeleted }
                .sortedBy { measurement ->  measurement.time }
        )
    }
    var result: Result? = null
        private set

    init {
        viewModelScope.launch {
            resultWithMeasurements.collectLatest {
                result = it?.result
            }
        }
    }

    fun startMeasuring() {
        viewModelScope.launch {
            resultRepository.insertResult(Result())
        }
    }

    fun deleteResult() {
        viewModelScope.launch {
            result?.let {
                resultRepository.deleteResult(it)
            }
        }
    }

    fun deleteMeasurement(measurementId: Int) {
        viewModelScope.launch {
            measurementRepository.deleteMeasurement(measurementId)
        }
    }

    fun saveResult() {
        viewModelScope.launch {
            result?.let {
                resultRepository.saveResult(it.id)
            }
        }
    }
}