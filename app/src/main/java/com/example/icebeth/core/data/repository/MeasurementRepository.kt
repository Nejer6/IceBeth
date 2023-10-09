package com.example.icebeth.core.data.repository

import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.asCreateRequest
import com.example.icebeth.core.data.database.model.asExternalModel
import com.example.icebeth.core.data.database.model.asUpdateRequest
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.core.model.asEntity
import com.example.icebeth.core.data.network.api.MeasurementApi
import com.example.icebeth.core.data.network.model.response.asEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasurementRepository @Inject constructor(
    private val measurementDao: MeasurementDao,
    private val measurementApi: MeasurementApi
) {
    fun getMeasurementsByResultId(resultId: Int) =
        measurementDao.getMeasurementsByResultId(resultId).map { list ->
            list.map { it.asExternalModel() }
        }

    suspend fun insertMeasurement(measurement: Measurement) {
        val measurementEntity = measurement.asEntity()
        measurementDao.insertMeasurement(measurementEntity)
    }

    suspend fun deleteMeasurement(measurementId: Int) {
        measurementDao.markMeasurementAsDeleted(measurementId)
    }

    private suspend fun syncMeasurement(measurementEntity: MeasurementEntity) {
        if (!measurementEntity.isUploaded) {
            when (val response =
                measurementApi.createMeasurement(measurementEntity.asCreateRequest())) {
                is ApiResponse.Success -> {
                    measurementDao.deleteMeasurementById(measurementEntity.id)
                    measurementDao.insertMeasurement(response.body.asEntity())
                }

                else -> {}
            }
            return
        }

        if (measurementEntity.isDeleted) {
            when (measurementApi.deleteMeasurement(measurementEntity.id)) {
                is ApiResponse.Success -> {
                    measurementDao.deleteMeasurementById(measurementEntity.id)
                }

                else -> {}
            }
            return
        }

        if (measurementEntity.isUpdated) {
            when (measurementApi.updateMeasurement(
                measurementEntity.asUpdateRequest(),
                measurementEntity.id
            )) {
                is ApiResponse.Success -> {
                    measurementDao.insertMeasurement(
                        measurementEntity.copy(
                            isUpdated = false
                        )
                    )
                }

                else -> {}
            }
            return
        }
    }

    suspend fun syncMeasurements() {
        val localMeasurements = measurementDao.getAllMeasurements()

        localMeasurements.collect { list ->
            list.forEach { measurementEntity ->
                syncMeasurement(measurementEntity)
            }
        }
    }
}