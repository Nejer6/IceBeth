package com.example.icebeth.feature.measurements

import com.example.icebeth.core.data.network.model.response.MeasurementResponse

data class MeasurementsState(
    val measurementResponses: List<MeasurementResponse> = listOf(),
    val resultId: Int,
    val number: Int
)
