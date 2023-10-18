package com.example.icebeth.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.data.database.model.ResultEntity
import com.example.icebeth.core.data.database.model.ResultWithMeasurements
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Query("SELECT * FROM results WHERE is_active = 1")
    fun getActiveResultWithMeasurements(): Flow<ResultWithMeasurements?>

    @Query("SELECT * FROM results WHERE (remote_id IS NULL) AND (is_active = 0)")
    suspend fun getAllUnloadedResultsWithMeasurements(): List<ResultWithMeasurements>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(resultEntity: ResultEntity)

    @Delete
    suspend fun deleteResult(resultEntity: ResultEntity)

    @Query("UPDATE results SET is_active = 0 WHERE id = :resultId")
    suspend fun markResultAsInactive(resultId: Int)

    @Query("SELECT COUNT(*) FROM results WHERE remote_id IS NULL")
    fun getCountOfResultsWithNullRemoteId(): Flow<Int>

    @Query("SELECT * FROM results WHERE is_active = 0")
    fun getAllInactiveResultsWithMeasurements(): Flow<List<ResultWithMeasurements>>
}