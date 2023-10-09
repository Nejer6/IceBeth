package com.example.icebeth.core.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ResultWithMeasurements(
    @Embedded
    val result: ResultEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "resultId"
    )
    val measurements: List<MeasurementEntity>
)

fun ResultWithMeasurements.asExternalModel() = com.example.icebeth.core.model.ResultWithMeasurements(
    result = result.asExternalModel(),
    measurements = measurements.map { it.asExternalModel() }
)
