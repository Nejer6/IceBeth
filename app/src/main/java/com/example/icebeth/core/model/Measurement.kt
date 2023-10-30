package com.example.icebeth.core.model

import com.example.icebeth.core.data.database.model.MeasurementEntity
import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    val cylinderHeight: Float,
    val groundFrozzed: Boolean,
    val id: Int,
    val massOfSnow: Float,
    val snowCrust: Boolean,
    val snowHeight: Float,
    val resultId: Int,
    val time: Long,
    val isDeleted: Boolean = false,
    val isUpdated: Boolean = false,
    val remoteId: Int? = null,
    val remoteResultId: Int? = null,
    val latitude: Double,
    val longitude: Double
)

fun Measurement.asEntity() = MeasurementEntity(
    id,
    cylinderHeight,
    groundFrozzed,
    massOfSnow,
    snowCrust,
    snowHeight,
    resultId,
    time,
    isDeleted,
    isUpdated,
    remoteId,
    remoteResultId,
    latitude = latitude,
    longitude = longitude
)
