package com.example.icebeth.core.data.repository

import android.util.Log
import com.example.icebeth.common.util.ApiResponse
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.asCreateRequest
import com.example.icebeth.core.data.database.model.asExternalModel
import com.example.icebeth.core.data.database.model.asResultCreateRequest
import com.example.icebeth.core.data.network.api.MeasurementApi
import com.example.icebeth.core.data.network.api.ResultApi
import com.example.icebeth.core.model.Result
import com.example.icebeth.core.model.asEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(
    private val resultDao: ResultDao,
    private val resultApi: ResultApi,
    private val measurementApi: MeasurementApi,
    private val measurementDao: MeasurementDao
) {
    suspend fun insertResult(result: Result) = resultDao.insertResult(result.asEntity())

    fun getActiveResultWithMeasurements() = resultDao.getActiveResultWithMeasurements().map {
        it?.asExternalModel()
    }

    suspend fun deleteResult(result: Result) = resultDao.deleteResult(result.asEntity())

    suspend fun saveResult(resultId: Int) {
        resultDao.markResultAsInactive(resultId)
        uploadResults()
    }

    suspend fun uploadResults() {
        Log.d("Nejer", "uploadResults")
        val unloadedResultsWithMeasurements = resultDao.getAllUnloadedResultsWithMeasurements()
        Log.d("Nejer", unloadedResultsWithMeasurements.toString())
        unloadedResultsWithMeasurements.forEach { resultWithMeasurements ->

            when (val resultResponse = resultApi.createResult(resultWithMeasurements.asResultCreateRequest())) {
                is ApiResponse.Success -> {
                    Log.d("Nejer", resultResponse.body.toString())
                    resultDao.insertResult(resultWithMeasurements.result.copy(
                        remoteId = resultResponse.body.id
                    ))

                    resultWithMeasurements.measurements.forEach {  measurementEntity ->
                        val measurementResponse =
                            measurementApi.createMeasurement(measurementEntity.asCreateRequest(
                                resultResponse.body.id
                            ))

                        when (measurementResponse) {
                            is ApiResponse.Success -> {
                                Log.d("Nejer", measurementResponse.body.toString())
                                measurementDao.insertMeasurement(
                                    measurementEntity.copy(
                                        remoteId = measurementResponse.body.id,
                                        remoteResultId = measurementResponse.body.resultId
                                    )
                                )
                            }
                            else -> {
                                Log.d("Nejer", measurementResponse.toString())
                            }
                        }
                    }
                }
                else -> {
                    Log.d("Nejer", resultResponse.toString())
                }
            }
        }
    }

    fun getCountOfResultsWithNullRemoteId() = resultDao.getCountOfResultsWithNullRemoteId()
}