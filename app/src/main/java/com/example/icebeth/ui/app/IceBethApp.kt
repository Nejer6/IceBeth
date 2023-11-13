package com.example.icebeth.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.ui.navigation.AppNavigation

@Composable
fun IceBethApp(
    viewModel: AppViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(
        key1 = state.isNetworkAvailable,
        key2 = state.isExistUnaploadedResults
    ) {
        if (state.isNetworkAvailable && state.isExistUnaploadedResults) {
            viewModel.upload()
        }
    }

    AppNavigation()
}
