package com.example.icebeth.core.data.repository

import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.asExternalModel
import com.example.icebeth.core.model.Result
import com.example.icebeth.core.model.asEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(
    private val resultDao: ResultDao
) {
    suspend fun insertResult(result: Result) = resultDao.insertResult(result.asEntity())

    fun getActiveResultWithMeasurements() = resultDao.getActiveResultWithMeasurements().map {
        it?.asExternalModel()
    }
}