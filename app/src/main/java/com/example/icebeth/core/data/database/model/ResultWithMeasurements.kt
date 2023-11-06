package com.example.icebeth.core.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.icebeth.core.data.network.model.request.ResultCreateRequest

data class ResultWithMeasurements(
    @Embedded
    val result: ResultEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "result_id"
    )
    val measurements: List<MeasurementEntity>
)

fun ResultWithMeasurements.asResultCreateRequest(): ResultCreateRequest {
    val everyTenthMeasurements = measurements
        .filter { it.cylinderHeight != null }

    val averageSnowHeight = measurements
        .sumOf { it.snowHeight }.toDouble() / 100

    val density = everyTenthMeasurements
        .sumOf { it.massOfSnow!! / (10 * it.cylinderHeight!!) } / 10


    val snowLayerWaterSaturationThickness = everyTenthMeasurements
        .sumOf { it.snowLayerWaterSaturation!! }
        .toDouble() / 10

    val averageIceCrustThickness = everyTenthMeasurements
        .sumOf { it.iceCrustThickness!! }
        .toDouble() / 10

    val averageThawedWaterLayerThickness = everyTenthMeasurements
        .sumOf { it.thawedWaterLayerThickness!! }
        .toDouble() / 10

    //
    val snowWaterEquivalent = 10 * averageSnowHeight * density

    val snowWaterContent = 8 * snowLayerWaterSaturationThickness

    val iceCrustWaterSupply = 0.8f * averageIceCrustThickness

    val thawedWaterSupply = 10 * averageThawedWaterLayerThickness

    //
    val totalWaterSupply = snowWaterEquivalent + snowWaterContent + iceCrustWaterSupply +
            thawedWaterSupply

    ///
    val heightGreaterThan30 = measurements.count { it.snowHeight > 30 }
    val sum13 = measurements.count { it.snowHeight in 1..3 }
    val sum46 = measurements.count { it.snowHeight in 4..6 }

    return ResultCreateRequest(
        time = result.time,
        averageSnowHeight = averageSnowHeight,
        minSnowHeight = measurements.minOf { it.snowHeight },
        maxSnowHeight = measurements.maxOf { it.snowHeight },
        sumOfSnowHeights = measurements.sumOf { it.snowHeight },
        density = density,
        totalWaterSupply = totalWaterSupply,
        degreeOfCoverage = result.degreeOfCoverage!!,
        snowCoverCharacter = result.snowCoverCharacter!!.ordinal,
        snowConditionDescription = result.snowConditionDescription!!.ordinal,
        heightGreaterThan30 = heightGreaterThan30,
        sum13 = sum13,
        sum46 = sum46
    )
}
