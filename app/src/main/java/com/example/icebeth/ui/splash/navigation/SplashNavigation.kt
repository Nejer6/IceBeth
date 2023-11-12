package com.example.icebeth.ui.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.icebeth.ui.splash.SplashScreenDestination

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen(
    onAuthorized: () -> Unit,
    onUnauthorized: () -> Unit
) {
    composable(splashRoute) {
        SplashScreenDestination(
            onAuthorized = onAuthorized,
            onUnauthorized = onUnauthorized
        )
    }
}
