package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainRoute(
    navigate: (String) -> Unit,
    navigateUp: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getMeasurements()
    }

    MainScreen(
        state = viewModel.state,
        navigateUp = navigateUp,
        navigate = navigate,
        onEvent = viewModel::onEvent
    )
}