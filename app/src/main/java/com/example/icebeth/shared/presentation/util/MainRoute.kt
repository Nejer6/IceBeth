package com.example.icebeth.shared.presentation.util

sealed class MainRoute(val route: String) {
    object MainScreen : MainRoute("main")
    object InfoScreen : MainRoute("info")
}
