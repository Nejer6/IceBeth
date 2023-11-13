package com.example.icebeth.ui.screens.measurements

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MeasurementsRoute(
    viewModel: MeasurementsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    MeasurementsScreen(
        navigateUp = navigateUp,
        state = viewModel.measurementsState
    )
}
