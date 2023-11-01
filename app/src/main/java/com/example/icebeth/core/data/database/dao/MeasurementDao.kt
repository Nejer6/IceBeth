package com.example.icebeth.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.data.database.model.MeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Query("SELECT * FROM measurements")
    fun getAllMeasurements(): Flow<List<MeasurementEntity>>

    @Query("SELECT * FROM measurements WHERE result_id = :resultId")
    fun getMeasurementsByResultId(resultId: Int) : Flow<List<MeasurementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurement(measurement: MeasurementEntity)

    @Query("DELETE FROM measurements WHERE id = :measurementId")
    suspend fun deleteMeasurementById(measurementId: Int)

    @Query("DELETE FROM measurements WHERE result_id = :resultId")
    suspend fun deleteMeasurementsByResultId(resultId: Int)

    @Query("UPDATE measurements SET is_deleted = 1 WHERE id = :measurementId")
    suspend fun markMeasurementAsDeleted(measurementId: Int)
}