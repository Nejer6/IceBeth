package com.example.icebeth.core.domain

import com.example.icebeth.core.model.Measurement
import com.example.icebeth.features.measurements.domain.models.MeasurementCreateResult
import com.example.icebeth.core.domain.util.MeasurementError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateMeasurementUseCase @Inject constructor(
    private val measurementRepository: com.example.icebeth.core.data.repository.MeasurementRepository
) {
    suspend operator fun invoke(
        cylinderHeight: String,
        groundFrozzed: Boolean,
        massOfSnow: String,
        snowCrust: Boolean,
        snowHeight: String,
        id: Int,
        time: Long,
        resultId: Int,
        latitude: Double,
        longitude: Double
    ): MeasurementCreateResult {
        fun validate(string: String): MeasurementError? {
            return string.let {
                if (it.isBlank()) return@let MeasurementError.Empty
                if (it.toFloatOrNull() == null) return@let MeasurementError.NotNumber
                if (it.toFloat() < 0f) return@let MeasurementError.NegativeNumber
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
                cylinderHeightError, massOfSnowError, snowHeightError, false
            )
        }

        measurementRepository.insertMeasurement(
            Measurement(
                newCylinderHeight.toFloat(),
                groundFrozzed,
                id,
                newMassOfSnow.toFloat(),
                resultId = resultId,
                snowCrust = snowCrust,
                snowHeight = newSnowHeight.toFloat(),
                time = time,
                latitude = latitude,
                longitude = longitude
            )
        )

        return MeasurementCreateResult(
            isSuccess = true
        )
    }
}