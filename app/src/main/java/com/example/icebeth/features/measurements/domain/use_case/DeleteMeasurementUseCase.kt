package com.example.icebeth.features.measurements.domain.use_case

import com.example.icebeth.features.measurements.data.MeasurementRepository
import com.example.icebeth.core.network.model.response.MeasurementResponse
import com.example.icebeth.common.util.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteMeasurementUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke(
        measurementId: Int
    ): ApiResponse<MeasurementResponse> {
        return measurementRepository.deleteMeasurement(measurementId)
    }
}