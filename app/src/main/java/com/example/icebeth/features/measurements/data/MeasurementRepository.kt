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
}