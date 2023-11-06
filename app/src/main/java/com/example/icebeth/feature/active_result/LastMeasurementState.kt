package com.example.icebeth.feature.active_result

import com.example.icebeth.core.data.database.model.SoilSurfaceCondition

data class LastMeasurementState(
    val snowHeight: String = "",
    val cylinderHeight: String = "",
    val massOfSnow: String = "",
    val iceCrustThickness: String = "",
    val snowLayerWaterSaturation: String = "",
    val thawedWaterLayerThickness: String = "",
    val soilSurfaceCondition: SoilSurfaceCondition? = null,
    val snowCrust: Boolean = false,

    val isExpandedMeasurement: Boolean? = null
)
