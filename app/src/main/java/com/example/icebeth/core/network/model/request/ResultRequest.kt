package com.example.icebeth.core.network.model.request

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ResultRequest @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("average_snow_height") @EncodeDefault val averageSnowHeight: Float = 0f,
    @SerialName("min_snow_height") @EncodeDefault val minSnowHeight: Float = 0f,
    @SerialName("max_snow_height") @EncodeDefault val maxSnowHeight: Float = 0f,
    @EncodeDefault val time: Long = Date().time
)
