package com.example.icebeth.features.measurements.data

import com.example.icebeth.features.measurements.data.remote.MeasurementApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementApi: MeasurementApi
) {
    suspend fun getAllMeasurements() = measurementApi.getAllMeasurements()
}