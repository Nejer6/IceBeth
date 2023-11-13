package com.example.icebeth.ui.screens.measurementedit

import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError

data class MeasurementEditState(
    val snowHeight: String,
    val snowHeightError: MeasurementError? = null,

    val cylinderHeight: String? = null,
    val cylinderHeightError: MeasurementError? = null,

    val massOfSnow: String? = null,
    val massOfSnowError: MeasurementError? = null,

    val iceCrustThickness: String? = null,
    val iceCrustThicknessError: MeasurementError? = null,

    val snowLayerWaterCondition: String? = null,
    val snowLayerWaterConditionError: MeasurementError? = null,

    val thawedWaterLayerThickness: String? = null,
    val thawedWaterLayerThicknessError: MeasurementError? = null,

    val soilSurfaceCondition: SoilSurfaceCondition? = null,

    val snowCrust: Boolean? = null,

    val isExpandedMeasurement: Boolean
)
