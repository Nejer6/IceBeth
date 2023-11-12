package com.example.icebeth.ui.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.icebeth.ui.main.MainRoute

const val mainRoute = "main_route"

fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    navigate(mainRoute, navOptions)
}

fun NavGraphBuilder.mainScreen(
    openDrawer: () -> Unit,
    navigateToActiveResult: () -> Unit
) {
    composable(mainRoute) {
        MainRoute(
            openDrawer,
            navigateToActiveResult
        )
    }
}
