package com.example.icebeth.features.measurements.domain.models

import com.example.icebeth.core.network.model.response.MeasurementResponse
import com.example.icebeth.features.measurements.domain.util.MeasurementError
import com.example.icebeth.common.util.ApiResponse

data class MeasurementCreateResult(
    val cylinderHeightError: MeasurementError? = null,
    val massOfSnowError: MeasurementError? = null,
    val snowHeightError: MeasurementError? = null,
    val content: ApiResponse<MeasurementResponse>? = null
)
