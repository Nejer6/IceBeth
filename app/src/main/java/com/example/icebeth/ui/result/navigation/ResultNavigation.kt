package com.example.icebeth.ui.result.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icebeth.ui.result.ResultRoute

const val resultRoute = "result"
private const val RESULT_ID_ARG = "resultId"

class ResultArgs(
    val resultId: Int
) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            resultId = checkNotNull(savedStateHandle[RESULT_ID_ARG])
        )
}

fun NavController.navigateToResult(resultId: Int, navOptions: NavOptions? = null) {
    navigate("$resultRoute/$resultId", navOptions)
}

fun NavGraphBuilder.resultScreen(
    navigateUp: () -> Unit,
    navigateToMeasurements: (Int) -> Unit,
    navigateToResultEdit: (Int) -> Unit
) {
    composable(
        "$resultRoute/{$RESULT_ID_ARG}",
        arguments = listOf(
            navArgument(RESULT_ID_ARG) { type = NavType.IntType }
        )
    ) {
        ResultRoute(
            navigateUp = navigateUp,
            navigateToMeasurements = navigateToMeasurements,
            navigateToResultEdit = navigateToResultEdit
        )
    }
}
