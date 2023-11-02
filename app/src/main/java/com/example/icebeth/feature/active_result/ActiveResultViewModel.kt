package com.example.icebeth.feature.active_result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.preferences.AppPreferences
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.data.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ActiveResultViewModel @Inject constructor(
    appPreferences: AppPreferences,
    private val resultRepository: ResultRepository,
    private val measurementRepository: MeasurementRepository
) : ViewModel() {
    private val resultId = appPreferences.getActiveResultId() ?: runBlocking {
        val newResultId = resultRepository.createNewResult().toInt()
        appPreferences.setActiveResultId(newResultId)
        newResultId
    }

    val measurementsCountFlow = measurementRepository.getCountOfMeasurementsByResultIdFlow(resultId)

    var currentMeasurementNumber by mutableStateOf(runBlocking {
        val measurementsCount = measurementsCountFlow.first()
        if (measurementsCount < 100) {
            measurementsCount + 1
        } else {
            null
        }
    })

    fun saveMeasurement() {
        viewModelScope.launch {
            measurementRepository.insertMeasurement(
                MeasurementEntity(
                    id = 0,
                    resultId = resultId,
                    time = Date().time,
                    latitude = 0.0,
                    longitude = 0.0,
                    snowHeight = 1,
                    cylinderHeight = null,
                    iceCrustThickness = null,
                    massOfSnow = null,
                    snowCrust = null,
                    snowLayerWaterSaturation = null,
                    soilSurfaceCondition = null,
                    thawedWaterLayerThickness = null
                )
            )
            currentMeasurementNumber = if (currentMeasurementNumber!! == 100) {
                null
            } else {
                currentMeasurementNumber!! + 1
            }
        }
    }

}