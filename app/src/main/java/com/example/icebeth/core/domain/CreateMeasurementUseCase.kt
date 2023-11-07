package com.example.icebeth.core.domain

import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.data.repository.MeasurementRepository
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.core.model.MeasurementCreateResult
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateMeasurementUseCase @Inject constructor(
    private val measurementRepository: MeasurementRepository
) {
    suspend operator fun invoke(
        resultId: Int,
        latitude: Double,
        longitude: Double,
        snowHeight: String,
        cylinderHeight: String?,
        iceCrustThickness: String?,
        massOfSnow: String?,
        snowCrust: Boolean?,
        snowLayerWaterSaturation: String?,
        soilSurfaceCondition: SoilSurfaceCondition?,
        thawedWaterLayerThickness: String?,
        isExpanded: Boolean
    ): MeasurementCreateResult {
        val snowHeightError = validateInteger(snowHeight)

        if (!isExpanded) {
            if (snowHeightError != null) {
                return MeasurementCreateResult(
                    snowHeightError = snowHeightError,
                    isSuccess = false
                )
            } else {
                measurementRepository.insertMeasurement(
                    MeasurementEntity(
                        resultId = resultId,
                        time = Date().time,
                        latitude = latitude,
                        longitude = longitude,
                        snowHeight = snowHeight.toInt(),
                        cylinderHeight = null,
                        massOfSnow = null,
                        soilSurfaceCondition = null,
                        snowCrust = null,
                        iceCrustThickness = null,
                        snowLayerWaterSaturation = null,
                        thawedWaterLayerThickness = null
                    )
                )

                return MeasurementCreateResult(isSuccess = true)
            }
        }

        val cylinderHeightError = validateInteger(cylinderHeight!!)

        val newMassOfSnow = massOfSnow?.replace(',', '.')
        val massOfSnowError = validateDouble(newMassOfSnow!!)
        val iceCrustThicknessError = validateInteger(iceCrustThickness!!)
        val snowLayerWaterSaturationError = validateInteger(snowLayerWaterSaturation!!)
        val soilSurfaceConditionError = soilSurfaceCondition.let {
            if (it != null) {
                return@let null
            } else {
                return@let MeasurementError.Empty
            }
        }
        val thawedWaterLayerThicknessError = validateInteger(thawedWaterLayerThickness!!)

        if (
            cylinderHeightError != null ||
            massOfSnowError != null ||
            snowHeightError != null ||
            iceCrustThicknessError != null ||
            snowLayerWaterSaturationError != null ||
            soilSurfaceConditionError != null ||
            thawedWaterLayerThicknessError != null
        ) {
            return MeasurementCreateResult(
                cylinderHeightError = cylinderHeightError,
                massOfSnowError = massOfSnowError,
                snowHeightError = snowHeightError,
                iceCrustThicknessError = iceCrustThicknessError,
                snowLayerWaterSaturationError = snowLayerWaterSaturationError,
                soilSurfaceConditionError = soilSurfaceConditionError,
                thawedWaterLayerThicknessError = thawedWaterLayerThicknessError,
                isSuccess = false
            )
        }

        measurementRepository.insertMeasurement(
            MeasurementEntity(
                resultId = resultId,
                time = Date().time,
                latitude = latitude,
                longitude = longitude,
                snowHeight = snowHeight.toInt(),
                cylinderHeight = cylinderHeight.toInt(),
                massOfSnow = newMassOfSnow.toDouble(),
                soilSurfaceCondition = soilSurfaceCondition!!,
                snowCrust = snowCrust!!,
                iceCrustThickness = iceCrustThickness.toInt(),
                snowLayerWaterSaturation = snowLayerWaterSaturation.toInt(),
                thawedWaterLayerThickness = thawedWaterLayerThickness.toInt()
            )
        )

        return MeasurementCreateResult(isSuccess = true)
    }

    private fun validateDouble(string: String): MeasurementError? {
        return string.let {
            if (it.isBlank()) return@let MeasurementError.Empty
            if (it.toDoubleOrNull() == null) return@let MeasurementError.NotDouble
            if (it.toDouble() < 0f) return@let MeasurementError.NegativeNumber
            return@let null
        }
    }

    private fun validateInteger(string: String): MeasurementError? {
        return string.let {
            if (it.isBlank()) return@let MeasurementError.Empty
            if (it.toIntOrNull() == null) return@let MeasurementError.NotInt
            if (it.toInt() < 0) return@let MeasurementError.NegativeNumber
            return@let null
        }
    }
}
