package com.example.icebeth.features.measurements.domain.models

import com.example.icebeth.features.measurements.data.remote.response.Measurement
import com.example.icebeth.features.measurements.domain.util.MeasurementError
import com.example.icebeth.shared.util.ApiResponse

data class MeasurementCreateResult(
    val cylinderHeightError: MeasurementError? = null,
    val massOfSnowError: MeasurementError? = null,
    val snowHeightError: MeasurementError? = null,
    val content: ApiResponse<Measurement>? = null
)
