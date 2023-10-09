package com.example.icebeth.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.core.data.network.model.request.MeasurementCreateRequest
import com.example.icebeth.core.data.network.model.request.MeasurementUpdateRequest

@Entity(
    tableName = "measurements"
)
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cylinderHeight: Float,
    val groundFrozzed: Boolean,
    val massOfSnow: Float,
    val snowCrust: Boolean,
    val snowHeight: Float,
    val resultId: Int,
    val time: Long,
    val isUploaded: Boolean = false,
    val isDeleted: Boolean = false,
    val isUpdated: Boolean = false
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
    isUploaded = isUploaded,
    isDeleted = isDeleted,
    isUpdated = isUpdated
)

fun MeasurementEntity.asCreateRequest() = MeasurementCreateRequest(
    cylinderHeight,
    groundFrozzed,
    massOfSnow,
    snowCrust,
    snowHeight,
    resultId,
    time
)

fun MeasurementEntity.asUpdateRequest() = MeasurementUpdateRequest(
    cylinderHeight,
    groundFrozzed,
    massOfSnow,
    snowCrust,
    snowHeight,
    time
)