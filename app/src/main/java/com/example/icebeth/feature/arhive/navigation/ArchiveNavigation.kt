package com.example.icebeth.feature.arhive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.icebeth.feature.arhive.ArchiveRoute

const val archiveRoute = "archive_route"

fun NavController.navigateToArchive(navOptions: NavOptions? = null) {
    navigate(archiveRoute, navOptions)
}

fun NavGraphBuilder.archiveScreen(
    openDrawer: () -> Unit
) {
    composable(archiveRoute) {
        ArchiveRoute(
            openDrawer
        )
    }
}