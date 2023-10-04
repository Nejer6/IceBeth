package com.example.icebeth.core.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.database.model.MeasurementEntity

interface MeasurementDao {

    @Query("SELECT * FROM measurements")
    suspend fun getAllMeasurements(): List<MeasurementEntity>

    @Query("SELECT * FROM measurements WHERE resultId = :resultId")
    suspend fun getMeasurementsByResultId(resultId: Int) : List<MeasurementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurement(measurement: MeasurementEntity)

    @Query("DELETE FROM measurements WHERE id = :measurementId")
    suspend fun deleteMeasurementById(measurementId: Int)

    @Query("DELETE FROM measurements WHERE resultId = :resultId")
    suspend fun deleteMeasurementsByResultId(resultId: Int)
}