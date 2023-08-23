package com.example.icebeth.features.measurements.data

import com.example.icebeth.features.measurements.data.remote.MeasurementApi
import com.example.icebeth.features.measurements.data.remote.request.MeasurementCreateRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementApi: MeasurementApi
) {
    suspend fun getAllMeasurements() = measurementApi.getAllMeasurements()

    suspend fun createMeasurement(measurementCreateRequest: MeasurementCreateRequest) =
        measurementApi.createMeasurement(measurementCreateRequest)

    suspend fun updateMeasurement(measurementCreateRequest: MeasurementCreateRequest, id: Int) =
        measurementApi.updateMeasurement(measurementCreateRequest, id)

    suspend fun deleteMeasurement(measurementId: Int) =
        measurementApi.deleteMeasurement(measurementId)

    suspend fun getAllResults() = measurementApi.getAllResults()

    suspend fun deleteResult(resultId: Int) = measurementApi.deleteResult(resultId)

    suspend fun createResult() = measurementApi.createResult()
}