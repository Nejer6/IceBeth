package com.example.icebeth.core.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementUpdateRequest(
    @SerialName("cylinder_height") val cylinderHeight: Float,
    @SerialName("ground_frozzed") val groundFrozzed: Boolean,
    @SerialName("mass_of_snow") val massOfSnow: Float,
    @SerialName("snow_crust") val snowCrust: Boolean,
    @SerialName("snow_height") val snowHeight: Float,
    val time: Long
)