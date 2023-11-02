package com.example.icebeth.core.model

import com.example.icebeth.core.domain.util.MeasurementError

data class MeasurementCreateResult(
    val cylinderHeightError: MeasurementError? = null,
    val massOfSnowError: MeasurementError? = null,
    val snowHeightError: MeasurementError? = null,
    val isSuccess: Boolean
)
