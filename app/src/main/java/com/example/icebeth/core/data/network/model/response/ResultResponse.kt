package com.example.icebeth.core.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val id: Int,
    @SerialName("average_snow_height") val averageSnowHeight: Float,
    @SerialName("min_snow_height") val minSnowHeight: Float,
    @SerialName("max_snow_height") val maxSnowHeight: Float,
    val time: Long
)