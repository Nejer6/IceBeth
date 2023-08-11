package com.example.icebeth.features.measurements.domain.util

sealed class MeasurementError {
    object NotNumber : MeasurementError()
    object Empty : MeasurementError()
}
