package com.example.icebeth.ui.screens.archive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.icebeth.ui.screens.archive.ArchiveRoute

const val archiveRoute = "archive"

fun NavController.navigateToArchive(navOptions: NavOptions? = null) {
    navigate(archiveRoute, navOptions)
}

fun NavGraphBuilder.archiveScreen(
    openDrawer: () -> Unit,
    navigateToResult: (Int) -> Unit
) {
    composable(archiveRoute) {
        ArchiveRoute(
            openDrawer = openDrawer,
            navigateToResult = navigateToResult
        )
    }
}
