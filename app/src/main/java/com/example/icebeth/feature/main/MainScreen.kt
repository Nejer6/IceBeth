package com.example.icebeth.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.icebeth.common.presentation.theme.spacing

@Composable
fun MainRoute(
    openDrawer: () -> Unit
) {
    MainScreen(
        openDrawer = openDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    openDrawer: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Снегосъемка") },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Меню")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { onEvent(ResultsEvent.CreateResult) }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Добавить съемку"
//                )
//            }
//        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
//            itemsIndexed(state.results, key = { _, result ->
//                result.id
//            }) { index, result ->
//                ResultCard(result, navigate, onEvent, state.results.size - index)
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
//            }
        }
    }
}