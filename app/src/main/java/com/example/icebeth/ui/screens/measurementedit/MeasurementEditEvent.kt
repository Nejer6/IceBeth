package com.example.icebeth.ui.screens.measurementedit

import com.example.icebeth.core.data.database.model.SoilSurfaceCondition

sealed class MeasurementEditEvent {
    data class ChangeSnowHeight(val value: String) : MeasurementEditEvent()
    data class ChangeCylinderHeight(val value: String) : MeasurementEditEvent()
    data class ChangeMassOfSnow(val value: String) : MeasurementEditEvent()
    data class ChangeIceCrustThickness(val value: String) : MeasurementEditEvent()
    data class ChangeSnowLayerWaterCondition(val value: String) : MeasurementEditEvent()
    data class ChangeThawedWaterLayerThickness(val value: String) : MeasurementEditEvent()
    data class ChangeSoilSurfaceCondition(val value: SoilSurfaceCondition) : MeasurementEditEvent()
    data object ToggleSnowCrust : MeasurementEditEvent()
    data object Save : MeasurementEditEvent()
}
