package com.example.icebeth.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.icebeth.feature.splash.SplashScreenDestination

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
