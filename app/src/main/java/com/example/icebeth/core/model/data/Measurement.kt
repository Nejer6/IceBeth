package com.example.icebeth.core.model.data

import com.example.icebeth.core.database.model.MeasurementEntity

data class Measurement(
    val cylinderHeight: Float,
    val groundFrozzed: Boolean,
    val id: Int,
    val massOfSnow: Float,
    val snowCrust: Boolean,
    val snowHeight: Float,
    val resultId: Int,
    val time: Long,
    val isUploaded: Boolean = false,
    val isDeleted: Boolean = false,
    val isUpdated: Boolean = false
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
    isUploaded,
    isDeleted,
    isUpdated
)
