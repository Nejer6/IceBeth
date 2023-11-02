package com.example.icebeth.feature.active_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.icebeth.feature.active_result.ActiveResultRoute

const val activeResultRoute = "active_result"

fun NavController.navigateToActiveResult() {
    navigate(activeResultRoute)
}

fun NavGraphBuilder.activeResultScreen(
    navigateToMain: () -> Unit
) {
    composable(activeResultRoute) {
        ActiveResultRoute(
            navigateToMain = navigateToMain
        )
    }
}