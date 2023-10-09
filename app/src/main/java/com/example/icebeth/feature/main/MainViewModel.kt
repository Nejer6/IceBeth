package com.example.icebeth.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.core.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val resultRepository: ResultRepository,
    private val measurementRepository: MeasurementRepository
) : ViewModel() {

    val resultWithMeasurements = resultRepository.getActiveResultWithMeasurements()
    var resultId: Int? = null
        private set

    init {
        viewModelScope.launch {
            resultWithMeasurements.collectLatest {
                resultId = it?.result?.id
            }
        }
    }

    fun startMeasuring() {
        viewModelScope.launch {
            resultRepository.insertResult(Result())
        }
    }

    fun addMeasurement() {
        viewModelScope.launch {
            if (resultId != null) {
                measurementRepository.insertMeasurement(
                    Measurement(
                        cylinderHeight = 0f,
                        groundFrozzed = false,
                        id = 0,
                        massOfSnow = 0f,
                        resultId = resultId!!,
                        snowCrust = false,
                        snowHeight = 0f,
                        time = Date().time
                    )
                )
            }
        }
    }
}