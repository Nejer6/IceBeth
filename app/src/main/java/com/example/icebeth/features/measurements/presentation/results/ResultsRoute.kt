package com.example.icebeth.features.measurements.presentation.results

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultsRoute(
    openDrawer: () -> Unit,
    navigate: (String) -> Unit,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getResults()
    }

    ResultsScreen(
        openDrawer = openDrawer,
        navigate = navigate,
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}