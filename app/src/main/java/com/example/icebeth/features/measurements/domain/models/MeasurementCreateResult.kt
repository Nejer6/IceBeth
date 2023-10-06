package com.example.icebeth.features.measurements.domain.models

import com.example.icebeth.features.measurements.domain.util.MeasurementError

data class MeasurementCreateResult(
    val cylinderHeightError: MeasurementError? = null,
    val massOfSnowError: MeasurementError? = null,
    val snowHeightError: MeasurementError? = null,
    val isSuccess: Boolean
)
