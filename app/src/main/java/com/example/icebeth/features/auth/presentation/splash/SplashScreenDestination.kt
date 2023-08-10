package com.example.icebeth.features.auth.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.shared.presentation.util.UiEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenDestination(
    onNavigate: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEffect.Navigate -> onNavigate(it.route)
                else -> Unit
            }
        }
    }
    SplashScreen()
}