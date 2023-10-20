package com.example.icebeth.feature.add_measurement

import com.example.icebeth.core.domain.util.MeasurementError
import java.util.Date

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
    val id: Int = 0,
    val resultId: Int,
    val time: Long = Date().time
)

enum class TypeMeasurement {
    ADD,
    EDIT
}
