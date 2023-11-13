package com.example.icebeth.ui.screens.measurements

sealed class MeasurementsEffect {
    data object InEditMode : MeasurementsEffect()
}
