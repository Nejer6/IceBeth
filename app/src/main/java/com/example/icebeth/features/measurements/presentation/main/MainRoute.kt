package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainRoute(
    openDrawer: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    MainScreen(
        state = viewModel.state,
        openDrawer = openDrawer
    )
}