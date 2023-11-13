package com.example.icebeth.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icebeth.util.getCorrectEnding

@Composable
fun MainRoute(
    openDrawer: () -> Unit,
    navigateToActiveResult: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val countOfResultsWithNullRemoteId by viewModel
        .countOfResultsWithNullRemoteIdFlow
        .collectAsState(
            initial = 0
        )

    MainScreen(
        openDrawer = openDrawer,
        onStartMeasuring = navigateToActiveResult,
        countOfResultsWithNullRemoteId = countOfResultsWithNullRemoteId
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    openDrawer: () -> Unit,
    onStartMeasuring: () -> Unit,
    countOfResultsWithNullRemoteId: Int
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Снегосъемка"

                    )
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Меню")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Начать съемку") },
                icon = { Icon(imageVector = Icons.Default.Start, contentDescription = null) },
                onClick = onStartMeasuring
            )
        }
    ) { paddingValues ->
        Box(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (countOfResultsWithNullRemoteId > 0) {
                Text(
                    text = "Не загружено на сервер: $countOfResultsWithNullRemoteId ${
                    "cъем".getCorrectEnding(
                        countOfResultsWithNullRemoteId,
                        "ка",
                        "ки",
                        "ок"
                    )
                    }.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Text(
                    text = "Все съемки отправлены.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
