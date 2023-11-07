package com.example.icebeth.core.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val id: Int,

    val time: Long,

    @SerialName("sum_of_snow_heights") val sumOfSnowHeights: Int,
    val density: Double,
    @SerialName("total_water_supply") val totalWaterSupply: Double,

    @SerialName("degree_of_coverage") val degreeOfCoverage: Int,
    @SerialName("snow_cover_character") val snowCoverCharacter: Int,
    @SerialName("snow_condition_description") val snowConditionDescription: Int,

    @SerialName("average_snow_height") val averageSnowHeight: Double,
    @SerialName("min_snow_height") val minSnowHeight: Int,
    @SerialName("max_snow_height") val maxSnowHeight: Int,

    @SerialName("height_greater_than_30") val heightGreaterThan30: Int,

    @SerialName("sum_1_3") val sum13: Int,
    @SerialName("sum_4_6") val sum46: Int
)
