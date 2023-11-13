package com.example.icebeth.core.model

import androidx.compose.runtime.Stable
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.network.model.request.ResultCreateRequest
import com.example.icebeth.core.data.network.model.request.ResultUpdateRequest

@Stable
data class Result(
    val id: Int,
    val time: Long,
    val measurements: List<MeasurementEntity>,
    val averageSnowHeight: Double,
    val minSnowHeight: Int,
    val maxSnowHeight: Int,
    val sumOfSnowHeights: Int,
    val density: Double,
    val totalWaterSupply: Double,
    val degreeOfCoverage: Int,
    val snowCoverCharacter: SnowCoverCharacter,
    val snowConditionDescription: SnowConditionDescription,
    val heightGreaterThan30: Int,
    val sum13: Int,
    val sum46: Int
)

fun Result.asResultCreateRequest() = ResultCreateRequest(
    time = time,
    averageSnowHeight = averageSnowHeight,
    minSnowHeight = minSnowHeight,
    maxSnowHeight = maxSnowHeight,
    sumOfSnowHeights = sumOfSnowHeights,
    density = density,
    totalWaterSupply = totalWaterSupply,
    degreeOfCoverage = degreeOfCoverage,
    snowCoverCharacter = snowCoverCharacter.ordinal,
    snowConditionDescription = snowConditionDescription.ordinal,
    heightGreaterThan30 = heightGreaterThan30,
    sum13 = sum13,
    sum46 = sum46
)

fun Result.asResultUpdateRequest() = ResultUpdateRequest(
    averageSnowHeight = averageSnowHeight,
    minSnowHeight = minSnowHeight,
    maxSnowHeight = maxSnowHeight,
    sumOfSnowHeights = sumOfSnowHeights,
    density = density,
    totalWaterSupply = totalWaterSupply,
    degreeOfCoverage = degreeOfCoverage,
    snowCoverCharacter = snowCoverCharacter.ordinal,
    snowConditionDescription = snowConditionDescription.ordinal,
    heightGreaterThan30 = heightGreaterThan30,
    sum13 = sum13,
    sum46 = sum46
)
