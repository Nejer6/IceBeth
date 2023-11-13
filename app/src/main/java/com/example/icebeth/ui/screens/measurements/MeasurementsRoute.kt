package com.example.icebeth.ui.screens.measurements

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.ui.components.ObserveAsEffects

@Composable
fun MeasurementsRoute(
    viewModel: MeasurementsViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToMeasurementEdit: (Int) -> Unit
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    ObserveAsEffects(flow = viewModel.effect) {
        when (it) {
            MeasurementsEffect.InEditMode -> {
                snackbarHostState.showSnackbar(
                    message = "Нажмите на измерение, которое нужно отредактировать.",
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    MeasurementsScreen(
        navigateUp = navigateUp,
        state = viewModel.state,
        snackbarHostState = snackbarHostState,
        onEvent = remember {
            {
                viewModel.onEvent(it)
            }
        },
        navigateToMeasurementEdit = navigateToMeasurementEdit
    )
}
