package com.example.icebeth.feature.archive

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.common.presentation.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveScreen(
    openDrawer: () -> Unit,
    state: ArchiveState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Архив")
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Меню")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            items(state.results) {
                Card(onClick = { /*TODO*/ }) {
                    Text(text = it.time.toString())
                }
            }
        }
    }
}
