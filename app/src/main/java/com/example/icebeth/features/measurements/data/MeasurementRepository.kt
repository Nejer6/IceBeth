package com.example.icebeth.features.measurements.data

import com.example.icebeth.features.measurements.data.remote.MeasurementApi
import com.example.icebeth.features.measurements.data.remote.request.MeasurementCreateRequest
import com.example.icebeth.features.measurements.data.remote.request.MeasurementUpdateRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementApi: MeasurementApi
) {
    suspend fun getAllMeasurements(resultId: Int) = measurementApi.getAllMeasurements(resultId)

    suspend fun createMeasurement(measurementCreateRequest: MeasurementCreateRequest) =
        measurementApi.createMeasurement(measurementCreateRequest)

    suspend fun updateMeasurement(measurementUpdateRequest: MeasurementUpdateRequest, id: Int) =
        measurementApi.updateMeasurement(measurementUpdateRequest, id)

    suspend fun deleteMeasurement(measurementId: Int) =
        measurementApi.deleteMeasurement(measurementId)

    suspend fun getAllResults() = measurementApi.getAllResults()

    suspend fun deleteResult(resultId: Int) = measurementApi.deleteResult(resultId)

    suspend fun createResult() = measurementApi.createResult()
}