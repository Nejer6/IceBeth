package com.example.icebeth.features.measurements.presentation.main

import com.example.icebeth.core.network.model.response.MeasurementResponse

data class MainState(
    val measurementResponses: List<MeasurementResponse> = listOf(),
    val resultId: Int,
    val number: Int
)
