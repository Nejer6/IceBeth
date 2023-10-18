package com.example.icebeth.core.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.icebeth.common.util.average
import com.example.icebeth.core.data.network.model.request.ResultCreateRequest
import com.example.icebeth.core.model.InactiveResult

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

fun ResultWithMeasurements.asResultCreateRequest() = ResultCreateRequest(
    time = result.time,
    averageSnowHeight = measurements.average { it.snowHeight },
    minSnowHeight = measurements.minOf { it.snowHeight },
    maxSnowHeight = measurements.maxOf { it.snowHeight }
)

fun ResultWithMeasurements.asInactiveResult() = InactiveResult(
    time = result.time,
    averageSnowHeight = measurements.average { it.snowHeight },
    minSnowHeight = measurements.minOf { it.snowHeight },
    maxSnowHeight = measurements.maxOf { it.snowHeight },
    id = result.id
)
