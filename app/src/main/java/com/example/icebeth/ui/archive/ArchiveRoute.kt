package com.example.icebeth.ui.archive

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArchiveRoute(
    openDrawer: () -> Unit,
    viewModel: ArchiveViewModel = hiltViewModel(),
    navigateToResult: (Int) -> Unit
) {
    ArchiveScreen(
        openDrawer = openDrawer,
        state = viewModel.state,
        navigateToResult = navigateToResult
    )
}
