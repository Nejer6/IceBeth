package com.example.icebeth.features.measurements.presentation.main

import com.example.icebeth.core.data.model.Measurement

data class MainState(
    val measurements: List<Measurement> = listOf(),
    val resultId: Int,
    val number: Int
)
