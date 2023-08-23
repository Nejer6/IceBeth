package com.example.icebeth.features.measurements.presentation.results

sealed class ResultsEvent {
    object CreateResult : ResultsEvent()
    data class Delete(val id: Int) : ResultsEvent()
}
