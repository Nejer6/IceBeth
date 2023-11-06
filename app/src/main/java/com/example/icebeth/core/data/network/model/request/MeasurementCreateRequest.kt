package com.example.icebeth.core.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementCreateRequest(
    @SerialName("result_id") val resultId: Int,

    val time: Long,
    val latitude: Double,
    val longitude: Double,

    @SerialName("snow_height") val snowHeight: Int,

    @SerialName("cylinder_height") val cylinderHeight: Int?,
    @SerialName("mass_of_snow") val massOfSnow: Double?,

    @SerialName("soil_surface_condition") val soilSurfaceCondition: Int?,
    @SerialName("snow_crust") val snowCrust: Boolean?,

    @SerialName("ice_crust_thickness") val iceCrustThickness: Int?,
    @SerialName("snow_layer_water_saturation") val snowLayerWaterSaturation: Int?,
    @SerialName("thawed_water_layer_thickness") val thawedWaterLayerThickness: Int?
)