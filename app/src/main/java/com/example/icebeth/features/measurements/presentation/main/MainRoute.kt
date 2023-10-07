package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainRoute(
    navigate: (String) -> Unit,
    navigateUp: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val measurements by viewModel.measurements.collectAsState(initial = emptyList())

    MainScreen(
        measurements = measurements,
        state = viewModel.state,
        navigateUp = navigateUp,
        navigate = navigate,
        onEvent = viewModel::onEvent
    )
}