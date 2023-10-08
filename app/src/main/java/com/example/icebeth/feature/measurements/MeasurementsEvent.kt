package com.example.icebeth.feature.measurements

sealed class MeasurementsEvent {
    data class Delete(val id: Int) : MeasurementsEvent()
}
