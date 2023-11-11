package com.example.icebeth.feature.result

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultRoute(
    viewModel: ResultViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToMeasurements: (Int) -> Unit
) {
    ResultScreen(
        state = viewModel.state,
        navigateUp = navigateUp,
        navigateToMeasurements = navigateToMeasurements
    )
}
