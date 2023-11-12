package com.example.icebeth.ui.resultedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultEditRoute(
    navigateUp: () -> Unit,
    viewModel: ResultEditViewModel = hiltViewModel()
) {
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
