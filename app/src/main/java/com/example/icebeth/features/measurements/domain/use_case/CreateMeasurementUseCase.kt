package com.example.icebeth.features.measurements.domain.use_case

import com.example.icebeth.features.measurements.data.MeasurementRepository
import com.example.icebeth.core.network.model.request.MeasurementCreateRequest
import com.example.icebeth.features.measurements.domain.models.MeasurementCreateResult
import com.example.icebeth.features.measurements.domain.util.MeasurementError
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateMeasurementUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke(
        cylinderHeight: String,
        groundFrozzed: Boolean,
        massOfSnow: String,
        snowCrust: Boolean,
        snowHeight: String,
        resultId: Int
    ): MeasurementCreateResult {
        fun validate(string: String): MeasurementError? {
            return string.let {
                if (it.isBlank()) return@let MeasurementError.Empty
                if (it.toFloatOrNull() == null) return@let MeasurementError.NotNumber
                return@let null
            }
        }

        val newCylinderHeight = cylinderHeight.replace(',', '.')
        val newMassOfSnow = massOfSnow.replace(',', '.')
        val newSnowHeight = snowHeight.replace(',', '.')

        val cylinderHeightError = validate(newCylinderHeight)
        val massOfSnowError = validate(newMassOfSnow)
        val snowHeightError = validate(newSnowHeight)

        if (cylinderHeightError != null || massOfSnowError != null || snowHeightError != null) {
            return MeasurementCreateResult(
                cylinderHeightError, massOfSnowError, snowHeightError
            )
        }

        return MeasurementCreateResult(
            content = measurementRepository.createMeasurement(
                MeasurementCreateRequest(
                    newCylinderHeight.toFloat(),
                    groundFrozzed,
                    newMassOfSnow.toFloat(),
                    snowCrust,
                    newSnowHeight.toFloat(),
                    resultId,
                    Date().time
                )
            )
        )
    }
}