package com.example.icebeth.core.domain.util

sealed class MeasurementError {
    object NotInt : MeasurementError()
    object NotDouble : MeasurementError()
    object Empty : MeasurementError()
    object NegativeNumber : MeasurementError()
}
