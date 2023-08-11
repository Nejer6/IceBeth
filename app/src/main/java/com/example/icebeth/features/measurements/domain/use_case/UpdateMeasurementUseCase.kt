package com.example.icebeth.features.measurements.domain.use_case

import com.example.icebeth.features.measurements.data.MeasurementRepository
import com.example.icebeth.features.measurements.data.remote.request.MeasurementCreateRequest
import com.example.icebeth.features.measurements.domain.models.MeasurementCreateResult
import com.example.icebeth.features.measurements.domain.util.MeasurementError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateMeasurementUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke(
        cylinderHeight: String,
        groundFrozzed: Boolean,
        massOfSnow: String,
        snowCrust: Boolean,
        snowHeight: String,
        id: Int
    ): MeasurementCreateResult {
        val cylinderHeightError = if (cylinderHeight.toFloatOrNull() == null) MeasurementError.NotNumber else null
        val massOfSnowError = if (massOfSnow.toFloatOrNull() == null) MeasurementError.NotNumber else null
        val snowHeightError = if (snowHeight.toFloatOrNull() == null) MeasurementError.NotNumber else null

        if (cylinderHeightError != null || massOfSnowError != null || snowHeightError != null) {
            return MeasurementCreateResult(
                cylinderHeightError, massOfSnowError, snowHeightError
            )
        }

        return MeasurementCreateResult(content = measurementRepository.updateMeasurement(
            MeasurementCreateRequest(
                cylinderHeight.toFloat(),
                groundFrozzed,
                massOfSnow.toFloat(),
                snowCrust,
                snowHeight.toFloat()
            ),
            id
        ))
    }
}