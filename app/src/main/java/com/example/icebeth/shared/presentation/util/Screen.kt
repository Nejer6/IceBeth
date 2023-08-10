package com.example.icebeth.shared.presentation.util

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash")
    object LoginScreen : Screen("login")
    object MainScreen : Screen("main")
}
