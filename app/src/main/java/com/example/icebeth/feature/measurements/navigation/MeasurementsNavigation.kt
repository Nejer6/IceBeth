package com.example.icebeth.feature.measurements.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val measurementsRoute = "measurements_route"

fun NavController.navigateToMeasurements(navOptions: NavOptions? = null) {
    navigate(measurementsRoute, navOptions)
}