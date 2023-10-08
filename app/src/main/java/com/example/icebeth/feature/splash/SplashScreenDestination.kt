package com.example.icebeth.feature.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.common.presentation.util.AppRoute

@Composable
fun SplashScreenDestination(
    onNavigate: (String) -> Unit,
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
        onNavigate(AppRoute.MainRoute.route)
    } else {
        onNavigate(AppRoute.LoginScreen.route)
    }

    SplashScreen()
}