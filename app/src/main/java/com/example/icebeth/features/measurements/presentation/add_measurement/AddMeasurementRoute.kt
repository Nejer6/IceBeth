package com.example.icebeth.features.measurements.presentation.add_measurement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.shared.presentation.util.UiEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddMeasurementRoute(
    navigateUp: () -> Unit,
    viewModel: AddMeasurementViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.effectFlow.collectLatest {
            when (it) {
                UiEffect.NavigateUp -> navigateUp()
                else -> {}
            }
        }
    }

    AddMeasurementScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}