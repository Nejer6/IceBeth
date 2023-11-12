package com.example.icebeth.ui.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreenDestination(
    onAuthorized: () -> Unit,
    onUnauthorized: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collect {
//            Log.d("Nejer", "Nejer")
//            when (it) {
//                is UiEffect.Navigate -> {
//                    onNavigate(it.route)
//                }
//
//                else -> {}
//            }
//        }
//    }
    if (viewModel.isAuthorized) {
        onAuthorized()
    } else {
        onUnauthorized()
    }

    SplashScreen()
}
