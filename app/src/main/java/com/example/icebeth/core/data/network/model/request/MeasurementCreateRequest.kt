package com.example.icebeth.core.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementCreateRequest(
    @SerialName("cylinder_height") val cylinderHeight: Float,
    @SerialName("ground_frozzed") val groundFrozzed: Boolean,
    @SerialName("mass_of_snow") val massOfSnow: Float,
    @SerialName("snow_crust") val snowCrust: Boolean,
    @SerialName("snow_height") val snowHeight: Float,
    @SerialName("result_id") val resultId: Int,
    val time: Long,
    val latitude: Double,
    val longitude: Double
)