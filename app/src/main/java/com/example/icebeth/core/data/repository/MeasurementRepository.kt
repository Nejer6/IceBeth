package com.example.icebeth.core.data.repository

import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementDao: MeasurementDao
) {

    suspend fun insertMeasurement(measurement: MeasurementEntity) {
        measurementDao.insertMeasurement(measurement)
    }

    suspend fun getCountOfMeasurementsByResultId(resultId: Int) =
        measurementDao.getCountOfMeasurementsByResultId(resultId)

    fun getMeasurementsByResultId(resultId: Int) =
        measurementDao.getMeasurementsByResultId(resultId)

    fun getCountOfMeasurementsByResultIdFlow(resultId: Int) =
        measurementDao.getCountOfMeasurementsByResultIdFlow(resultId)

    suspend fun updateMeasurement(
        measurementId: Int,
        snowHeight: Int,
        cylinderHeight: Int?,
        massOfSnow: Double?,
        soilSurfaceCondition: SoilSurfaceCondition?,
        snowCrust: Boolean?,
        iceCrustThickness: Int?,
        snowLayerWaterSaturation: Int?,
        thawedWaterLayerThickness: Int?
    ) = measurementDao.updateMeasurement(
        measurementId,
        snowHeight,
        cylinderHeight,
        massOfSnow,
        soilSurfaceCondition,
        snowCrust,
        iceCrustThickness,
        snowLayerWaterSaturation,
        thawedWaterLayerThickness
    )
}