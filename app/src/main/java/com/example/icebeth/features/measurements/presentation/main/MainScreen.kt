package com.example.icebeth.features.measurements.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.icebeth.features.measurements.presentation.main.components.MeasurementCard
import com.example.icebeth.shared.presentation.theme.IceBethTheme
import com.example.icebeth.shared.presentation.theme.spacing
import com.example.icebeth.shared.presentation.util.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainState,
    navigate: (String) -> Unit,
    onEvent: (MainEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Съемка №$") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigate(AppRoute.AddMeasurementScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить измерение"
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            items(state.measurements.size, key = {
                state.measurements[it].id
            }) { measureListId ->
                MeasurementCard(state, measureListId, navigate) { onEvent(MainEvent.Delete(it)) }
            }

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    IceBethTheme {
        MainScreen(
            MainState(),
            {},
            {}
        )
    }
}