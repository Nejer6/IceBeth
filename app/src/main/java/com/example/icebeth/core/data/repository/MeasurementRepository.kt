package com.example.icebeth.core.data.repository

import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.database.dao.MeasurementDao
import com.example.icebeth.core.database.model.asCreateRequest
import com.example.icebeth.core.database.model.asExternalModel
import com.example.icebeth.core.database.model.asUpdateRequest
import com.example.icebeth.core.model.data.Measurement
import com.example.icebeth.core.model.data.asEntity
import com.example.icebeth.core.network.model.api.MeasurementApi
import com.example.icebeth.core.network.model.response.asEntity
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

    fun insertMeasurement(measurement: Measurement) {
        measurementDao.insertMeasurement(measurement.asEntity())
    }

    suspend fun syncMeasurements() {
        val localMeasurements = measurementDao.getAllMeasurements()

        localMeasurements.collect { list ->
            list.forEach { measurementEntity ->
                if (!measurementEntity.isUploaded) {
                    when (val response =
                        measurementApi.createMeasurement(measurementEntity.asCreateRequest())) {
                        is ApiResponse.Success -> {
                            measurementDao.deleteMeasurementById(measurementEntity.id)
                            measurementDao.insertMeasurement(response.body.asEntity())
                        }

                        else -> {}
                    }
                    return@forEach
                }

                if (measurementEntity.isDeleted) {
                    when (measurementApi.deleteMeasurement(measurementEntity.id)) {
                        is ApiResponse.Success -> {
                            measurementDao.deleteMeasurementById(measurementEntity.id)
                        }

                        else -> {}
                    }
                    return@forEach
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
                    return@forEach
                }
            }
        }
    }
}