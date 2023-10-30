package com.example.icebeth.core.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.icebeth.core.data.network.model.request.MeasurementCreateRequest
import com.example.icebeth.core.model.Measurement

@Entity(
    tableName = "measurements",
    foreignKeys = [ForeignKey(
        entity = ResultEntity::class,
        parentColumns = ["id"],
        childColumns = ["resultId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "cylinder_height")
    val cylinderHeight: Float,
    @ColumnInfo(name = "ground_frozzed")
    val groundFrozzed: Boolean,
    @ColumnInfo(name = "mass_of_snow")
    val massOfSnow: Float,
    @ColumnInfo(name = "snow_crust")
    val snowCrust: Boolean,
    @ColumnInfo(name = "snow_height")
    val snowHeight: Float,
    val resultId: Int,
    val time: Long,
    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,
    @ColumnInfo(name = "is_updated")
    val isUpdated: Boolean = false,
    @ColumnInfo(name = "remote_id")
    val remoteId: Int? = null,
    @ColumnInfo(name = "remote_result_id")
    val remoteResultId: Int? = null,
    @ColumnInfo(defaultValue = "0.0")
    val latitude: Double = 0.0,
    @ColumnInfo(defaultValue = "0.0")
    val longitude: Double = 0.0
)

fun MeasurementEntity.asExternalModel() = Measurement(
    id = id,
    cylinderHeight = cylinderHeight,
    groundFrozzed = groundFrozzed,
    massOfSnow = massOfSnow,
    snowCrust = snowCrust,
    snowHeight = snowHeight,
    resultId = resultId,
    time = time,
    isDeleted = isDeleted,
    isUpdated = isUpdated,
    remoteId = remoteId,
    remoteResultId = remoteResultId,
    latitude = latitude,
    longitude = longitude
)

fun MeasurementEntity.asCreateRequest(remoteResultId: Int) = MeasurementCreateRequest(
    cylinderHeight,
    groundFrozzed,
    massOfSnow,
    snowCrust,
    snowHeight,
    remoteResultId,
    time,
    latitude = latitude,
    longitude = longitude
)
