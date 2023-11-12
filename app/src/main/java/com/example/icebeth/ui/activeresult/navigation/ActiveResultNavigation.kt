package com.example.icebeth.ui.activeresult.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.icebeth.ui.activeresult.ActiveResultRoute

const val activeResultRoute = "active_result"

fun NavController.navigateToActiveResult(navOptions: NavOptions? = null) {
    navigate(activeResultRoute, navOptions)
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
