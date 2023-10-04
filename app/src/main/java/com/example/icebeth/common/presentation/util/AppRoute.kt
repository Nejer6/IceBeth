package com.example.icebeth.common.presentation.util

sealed class AppRoute(val route: String) {
    object SplashScreen : AppRoute("splash")
    object LoginScreen : AppRoute("login")
    object MainRoute : AppRoute("main")
    object MeasurementsScreen : AppRoute("measurements")
    object AddMeasurementScreen : AppRoute("add_measurement")
}
