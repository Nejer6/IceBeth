package com.example.icebeth.ui.screens.measurementedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.ui.components.ObserveAsEffects

@Composable
fun MeasurementEditRoute(
    navigateUp: () -> Unit,
    viewModel: MeasurementEditViewModel = hiltViewModel()
) {
    ObserveAsEffects(flow = viewModel.effect) {
        when (it) {
            MeasurementEditEffect.NavigateUp -> navigateUp()
        }
    }

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
