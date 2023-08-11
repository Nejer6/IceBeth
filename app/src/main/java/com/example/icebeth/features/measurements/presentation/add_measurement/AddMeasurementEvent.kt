package com.example.icebeth.features.measurements.presentation.add_measurement

sealed class AddMeasurementEvent {
    data class SetCylinderHeight(val value: String) : AddMeasurementEvent()
    data class SetMassOfSnow(val value: String) : AddMeasurementEvent()
    data class SetSnowHeight(val value: String) : AddMeasurementEvent()
    object ToggleGroundFrozzed : AddMeasurementEvent()
    object ToggleSnowCrust : AddMeasurementEvent()

    object Close : AddMeasurementEvent()
    object Save : AddMeasurementEvent()
}
