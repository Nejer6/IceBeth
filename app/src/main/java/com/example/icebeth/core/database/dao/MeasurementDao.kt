package com.example.icebeth.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.database.model.MeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Query("SELECT * FROM measurements")
    fun getAllMeasurements(): Flow<List<MeasurementEntity>>

    @Query("SELECT * FROM measurements WHERE resultId = :resultId")
    fun getMeasurementsByResultId(resultId: Int) : Flow<List<MeasurementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeasurement(measurement: MeasurementEntity)

    @Query("DELETE FROM measurements WHERE id = :measurementId")
    fun deleteMeasurementById(measurementId: Int)

    @Query("DELETE FROM measurements WHERE resultId = :resultId")
    fun deleteMeasurementsByResultId(resultId: Int)
}