package com.example.icebeth.feature.measurements.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icebeth.feature.measurements.MeasurementsRoute

const val measurementsRoute = "measurements"
private const val RESULT_ID_ARG = "resultId"

fun NavController.navigateToMeasurements(resultId: Int, navOptions: NavOptions? = null) {
    navigate("$measurementsRoute/$resultId", navOptions)
}

class MeasurementsArgs(
    val resultId: Int
) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(resultId = checkNotNull(savedStateHandle[RESULT_ID_ARG]))
}

fun NavGraphBuilder.measurementsScreen(
    navigateUp: () -> Unit
) {
    composable(
        "$measurementsRoute/{$RESULT_ID_ARG}",
        arguments = listOf(
            navArgument(RESULT_ID_ARG) { type = NavType.IntType }
        )
    ) {
        MeasurementsRoute(
            navigateUp = navigateUp
        )
    }
}
