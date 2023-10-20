package com.example.icebeth.core.domain.util

sealed class MeasurementError {
    object NotNumber : MeasurementError()
    object Empty : MeasurementError()
    object NegativeNumber : MeasurementError()
}
