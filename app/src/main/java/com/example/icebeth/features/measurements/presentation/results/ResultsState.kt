package com.example.icebeth.features.measurements.presentation.results

import com.example.icebeth.features.measurements.data.remote.response.Result

data class ResultsState(
    val results: List<Result> = listOf()
)
