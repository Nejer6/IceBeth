package com.example.icebeth.feature.archive

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArchiveRoute(
    openDrawer: () -> Unit,
    viewModel: ArchiveViewModel = hiltViewModel()
) {
    ArchiveScreen(
        openDrawer = openDrawer,
        state = viewModel.state
    )
}
