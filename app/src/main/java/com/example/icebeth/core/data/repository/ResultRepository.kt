package com.example.icebeth.core.data.repository

import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.ResultEntity
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.database.model.asCreateRequest
import com.example.icebeth.core.data.database.model.asResult
import com.example.icebeth.core.data.network.api.MeasurementApi
import com.example.icebeth.core.data.network.api.ResultApi
import com.example.icebeth.core.data.network.util.ApiResponse
import com.example.icebeth.core.model.asResultCreateRequest
import com.example.icebeth.core.model.asResultUpdateRequest
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.map

@Singleton
class ResultRepository @Inject constructor(
    private val resultDao: ResultDao,
    private val resultApi: ResultApi,
    private val measurementApi: MeasurementApi,
    private val measurementDao: MeasurementDao
) {
    suspend fun deleteResultById(resultId: Int) = resultDao.deleteResultById(resultId)

    suspend fun uploadResults() {
        val unloadedResultsWithMeasurements = resultDao.getAllUnloadedResultsWithMeasurements()

        unloadedResultsWithMeasurements.forEach { resultWithMeasurements ->
            if (resultWithMeasurements.result.remoteId == null) {
                when (
                    val resultResponse =
                        resultApi.createResult(
                            resultWithMeasurements.asResult().asResultCreateRequest()
                        )
                ) {
                    is ApiResponse.Success -> {
                        val remoteId = resultResponse.body.id

                        resultDao.updateRemoteId(
                            resultId = resultWithMeasurements.result.id,
                            remoteId = remoteId
                        )

                        resultWithMeasurements.measurements.forEach {
                            measurementDao.updateRemoteResultId(it.id, remoteId)
                        }
                    }

                    else -> return
                }
            } else if (resultWithMeasurements.result.isUpdated) {
                when (
                    resultApi.updateResult(
                        resultWithMeasurements.result.remoteId,
                        resultWithMeasurements.asResult().asResultUpdateRequest()
                    )
                ) {
                    is ApiResponse.Success -> {
                        resultDao.updateIsUpdated(resultWithMeasurements.result.id, false)
                    }

                    else -> return
                }
            }
        }

        val unloadedMeasurements = measurementDao.getAllUnloadedMeasurements()

        unloadedMeasurements.forEach { measurementEntity ->
            when (
                val measurementResponse =
                    measurementApi.createMeasurement(
                        measurementEntity.asCreateRequest(measurementEntity.remoteResultId!!)
                    )
            ) {
                is ApiResponse.Success -> {
                    measurementDao.insertMeasurement(
                        measurementEntity.copy(
                            remoteId = measurementResponse.body.id,
                            remoteResultId = measurementResponse.body.resultId,
                            isUpdated = false
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

    suspend fun updateResult(
        resultId: Int,
        degreeOfCoverage: Int,
        snowCoverCharacter: SnowCoverCharacter,
        snowConditionDescription: SnowConditionDescription,
        isUpdated: Boolean
    ) = resultDao.updateResult(
        resultId = resultId,
        degreeOfCoverage = degreeOfCoverage,
        snowCoverCharacter = snowCoverCharacter,
        snowConditionDescription = snowConditionDescription,
        isUpdated = isUpdated
    )

    fun getAllResults() = resultDao.getAllResults()

    fun getResultById(resultId: Int) = resultDao
        .getResultWithMeasurementsById(resultId)
        .map { it.asResult() }
}
