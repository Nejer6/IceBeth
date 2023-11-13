package com.example.icebeth.ui.screens.measurementedit.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icebeth.ui.screens.measurementedit.MeasurementEditRoute

const val measurementEditRoute = "measurement_edit"
private const val MEASUREMENT_ID = "measurementId"

fun NavController.navigateToMeasurementEdit(measurementId: Int, navOptions: NavOptions? = null) {
    navigate("$measurementEditRoute/$measurementId", navOptions)
}

class MeasurementEditArgs(
    val measurementId: Int
) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            measurementId = checkNotNull(savedStateHandle[MEASUREMENT_ID])
        )
}

fun NavGraphBuilder.measurementEditScreen(
    navigateUp: () -> Unit
) {
    composable(
        "$measurementEditRoute/{$MEASUREMENT_ID}",
        arguments = listOf(
            navArgument(MEASUREMENT_ID) { type = NavType.IntType }
        )
    ) {
        MeasurementEditRoute(
            navigateUp = navigateUp
        )
    }
}
