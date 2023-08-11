package com.example.icebeth.features.measurements.presentation.add_measurement

import com.example.icebeth.features.measurements.domain.util.MeasurementError

data class AddMeasurementState(
    val cylinderHeight: String = "",
    val cylinderHeightError: MeasurementError? = null,
    val groundFrozzed: Boolean = false,
    val massOfSnow: String = "",
    val massOfSnowError: MeasurementError? = null,
    val snowCrust: Boolean = false,
    val snowHeight: String = "",
    val snowHeightError: MeasurementError? = null,
    val type: TypeMeasurement = TypeMeasurement.ADD,
    val id: Int = 0
)

enum class TypeMeasurement {
    ADD,
    EDIT
}
