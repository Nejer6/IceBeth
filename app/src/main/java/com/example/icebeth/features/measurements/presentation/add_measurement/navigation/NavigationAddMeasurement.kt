package com.example.icebeth.features.measurements.presentation.add_measurement.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.features.measurements.presentation.add_measurement.AddMeasurementRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val RESULT_ID_ARG = "resultId"
private const val MEASUREMENT_ARG = "measurement"
const val addMeasurementRoute = "add_measurement"

class AddMeasurementArgs(
    val resultId: Int,
    val measurement: Measurement?
) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                checkNotNull(savedStateHandle[RESULT_ID_ARG]),
                when (val encodedMeasurement: String? = savedStateHandle[MEASUREMENT_ARG]) {
                    is String -> Json.decodeFromString(encodedMeasurement)
                    else -> null
                }
            )
}

fun NavController.navigateToAddMeasurement(resultId: Int, measurement: Measurement?) {
    var route = "$addMeasurementRoute/$resultId"
    if (measurement != null) {
        val encodedMeasurement = Json.encodeToString(measurement)
        route += "?$MEASUREMENT_ARG=$encodedMeasurement"
    }
    navigate(route)
}

fun NavGraphBuilder.addMeasurementScreen(
    navigateUp: () -> Unit
) {
    composable(
        "$addMeasurementRoute/{$RESULT_ID_ARG}?$MEASUREMENT_ARG={$MEASUREMENT_ARG}",
        arguments = listOf(
            navArgument(RESULT_ID_ARG) { type = NavType.IntType },
            navArgument(MEASUREMENT_ARG) { nullable = true }
        )
    ) {
        AddMeasurementRoute(
            navigateUp = navigateUp
        )
    }
}

