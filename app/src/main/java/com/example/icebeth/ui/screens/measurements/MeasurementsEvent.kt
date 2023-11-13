package com.example.icebeth.ui.screens.measurements

sealed class MeasurementsEvent {
    data object ToggleEditMode : MeasurementsEvent()
}
