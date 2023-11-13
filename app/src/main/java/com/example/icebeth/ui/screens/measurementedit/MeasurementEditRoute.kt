package com.example.icebeth.ui.screens.measurementedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MeasurementEditRoute(
    navigateUp: () -> Unit,
    viewModel: MeasurementEditViewModel = hiltViewModel()
) {
    MeasurementEditScreen(
        state = viewModel.state,
        navigateUp = navigateUp,
        onEvent = remember {
            {
                viewModel.onEvent(it)
            }
        }
    )
}
