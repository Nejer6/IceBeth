package com.example.icebeth.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
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

    @Query("SELECT * FROM measurements WHERE remote_id IS NULL")
    fun getAllUnloadedMeasurements(): List<MeasurementEntity>

    @Query("SELECT COUNT(*) FROM measurements WHERE result_id = :resultId")
    suspend fun getCountOfMeasurementsByResultId(resultId: Int): Int

    @Query("SELECT COUNT(*) FROM measurements WHERE result_id = :resultId")
    fun getCountOfMeasurementsByResultIdFlow(resultId: Int): Flow<Int>

    @Query("UPDATE measurements SET remote_result_id = :remoteResultId WHERE id = :measurementId")
    fun updateRemoteResultId(measurementId: Int, remoteResultId: Int)

    @Query("UPDATE measurements SET " +
            "snow_height = :snowHeight, " +
            "cylinder_height = :cylinderHeight, " +
            "mass_of_snow = :massOfSnow, " +
            "soil_surface_condition = :soilSurfaceCondition, " +
            "snow_crust = :snowCrust, " +
            "ice_crust_thickness = :iceCrustThickness, " +
            "snow_layer_water_saturation = :snowLayerWaterSaturation, " +
            "thawed_water_layer_thickness = :thawedWaterLayerThickness " +
            "WHERE id = :measurementId")
    suspend fun updateMeasurement(
        measurementId: Int,
        snowHeight: Int,
        cylinderHeight: Int?,
        massOfSnow: Double?,
        soilSurfaceCondition: SoilSurfaceCondition?,
        snowCrust: Boolean?,
        iceCrustThickness: Int?,
        snowLayerWaterSaturation: Int?,
        thawedWaterLayerThickness: Int?
    )
}