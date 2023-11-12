package com.example.icebeth.ui.resultedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.ui.components.ObserveAsEffects

@Composable
fun ResultEditRoute(
    navigateUp: () -> Unit,
    viewModel: ResultEditViewModel = hiltViewModel()
) {
    ObserveAsEffects(flow = viewModel.effect) {
        when (it) {
            ResultEditEffect.NavigateUp -> navigateUp()
        }
    }

    ResultEditScreen(
        navigateUp = navigateUp,
        onEvent = remember {
            {
                viewModel.onEvent(it)
            }
        },
        state = viewModel.state
    )
}
