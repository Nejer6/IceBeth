package com.example.icebeth.features.measurements.presentation.main

sealed class MainEvent {
    data class Delete(val id: Int) : MainEvent()
}
