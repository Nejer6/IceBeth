package com.example.icebeth.core.data.repository

import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.ResultEntity
import com.example.icebeth.core.data.database.model.asCreateRequest
import com.example.icebeth.core.data.database.model.asResultCreateRequest
import com.example.icebeth.core.data.network.api.MeasurementApi
import com.example.icebeth.core.data.network.api.ResultApi
import com.example.icebeth.core.data.preferences.AppPreferences
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(
    private val resultDao: ResultDao,
    private val resultApi: ResultApi,
    private val measurementApi: MeasurementApi,
    private val measurementDao: MeasurementDao,
    private val appPreferences: AppPreferences
) {
    suspend fun insertResult(result: ResultEntity) = resultDao.insertResult(result)

    suspend fun saveResult() {
        appPreferences.setActiveResultId(null)
        uploadResults()
    }

    fun getActiveResultId() = appPreferences.getActiveResultId()

    suspend fun deleteResultById(resultId: Int) = resultDao.deleteResultById(resultId)

    suspend fun uploadResults() {
        val unloadedResultsWithMeasurements = resultDao.getAllUnloadedResultsWithMeasurements()

        unloadedResultsWithMeasurements.forEach { resultWithMeasurements ->
            when (val resultResponse =
                resultApi.createResult(resultWithMeasurements.asResultCreateRequest())) {
                is ApiResponse.Success -> {
                    resultDao.insertResult(
                        resultWithMeasurements.result.copy(
                            remoteId = resultResponse.body.id
                        )
                    )
                }

                else -> return
            }
        }

        val unloadedMeasurements = measurementDao.getAllUnloadedMeasurements()

        unloadedMeasurements.forEach { measurementEntity ->
            when (val measurementResponse =
                measurementApi.createMeasurement(measurementEntity.asCreateRequest(measurementEntity.resultId))
            ) {
                is ApiResponse.Success -> {
                    measurementDao.insertMeasurement(
                        measurementEntity.copy(
                            remoteId = measurementResponse.body.id,
                            remoteResultId = measurementResponse.body.resultId
                        )
                    )
                }

                else -> return
            }
        }
    }

    fun getCountOfResultsWithNullRemoteId() = resultDao.getCountOfResultsWithNullRemoteId()

    suspend fun createNewResult() = resultDao.insertResult(
        ResultEntity(
            id = 0,
            time = Date().time,
            remoteId = null,
            degreeOfCoverage = null,
            snowCoverCharacter = null,
            snowConditionDescription = null
        )
    )
}