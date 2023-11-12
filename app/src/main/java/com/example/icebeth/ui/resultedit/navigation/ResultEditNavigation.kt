package com.example.icebeth.ui.resultedit.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icebeth.ui.resultedit.ResultEditRoute

const val resultEditRoute = "result_edit"
private const val RESULT_ID = "resultId"

fun NavController.navigateToResultEdit(resultId: Int, navOptions: NavOptions? = null) {
    navigate("$resultEditRoute/$resultId", navOptions)
}

class ResultEditArgs(
    val resultId: Int
) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            resultId = checkNotNull(savedStateHandle[RESULT_ID])
        )
}

fun NavGraphBuilder.resultEditScreen(
    navigateUp: () -> Unit
) {
    composable(
        "$resultEditRoute/{$RESULT_ID}",
        arguments = listOf(
            navArgument(RESULT_ID) { type = NavType.IntType }
        )
    ) {
        ResultEditRoute(
            navigateUp = navigateUp
        )
    }
}
