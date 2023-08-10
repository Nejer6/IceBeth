package com.example.icebeth.features.measurements.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    @SerialName("cylinder_height") val cylinderHeight: Int,
    @SerialName("ground_frozzed") val groundFrozzed: Boolean,
    val id: Int,
    @SerialName("mass_of_snow") val massOfSnow: Int,
//    val owner_id: Int,
//    val result_id: Any,
    @SerialName("snow_crust") val snowCrust: Boolean,
    @SerialName("snow_height") val snowHeight: Int
)