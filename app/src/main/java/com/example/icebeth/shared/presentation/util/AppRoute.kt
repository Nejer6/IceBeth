package com.example.icebeth.shared.presentation.util

sealed class AppRoute(val route: String) {
    object SplashScreen : AppRoute("splash")
    object LoginScreen : AppRoute("login")
    object MainRoute : AppRoute("main")
}
