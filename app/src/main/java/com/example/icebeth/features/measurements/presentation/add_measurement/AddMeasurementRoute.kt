package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddMeasurementRoute(
    viewModel: AddMeasurementViewModel = hiltViewModel()
) {
    AddMeasurementScreen(state = viewModel.state, onEvent = viewModel::onEvent)
}