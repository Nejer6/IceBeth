package com.example.icebeth.features.measurements.presentation.main

import com.example.icebeth.features.measurements.data.remote.response.Measurement

data class MainState(
    val measurements: List<Measurement> = listOf(),
    val resultId: Int,
    val number: Int
)
