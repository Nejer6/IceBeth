package com.example.icebeth.core.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementCreateRequest(
    @SerialName("result_id") val resultId: Int,

    val time: Long,
    val latitude: Double,
    val longitude: Double,

    @SerialName("snow_height") val snowHeight: Float,

    @SerialName("cylinder_height") val cylinderHeight: Float?,
    @SerialName("mass_of_snow") val massOfSnow: Float?,

    @SerialName("soil_surface_condition") val soilSurfaceCondition: String?,
    @SerialName("snow_crust") val snowCrust: Boolean?,

    @SerialName("ice_crust_thickness") val iceCrustThickness: Float?,
    @SerialName("snow_layer_water_saturation") val snowLayerWaterSaturation: Float?,
    @SerialName("thawed_water_layer_thickness") val thawedWaterLayerThickness: Float?
)