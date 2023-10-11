package com.example.icebeth.core.data.repository

import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.core.model.asEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementDao: MeasurementDao
) {

    suspend fun insertMeasurement(measurement: Measurement) {
        val measurementEntity = measurement.asEntity()
        measurementDao.insertMeasurement(measurementEntity)
    }

    suspend fun deleteMeasurement(measurementId: Int) {
        measurementDao.markMeasurementAsDeleted(measurementId)
    }
}