package com.example.icebeth.feature.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.icebeth.common.util.formatDateFromTimestamp
import com.example.icebeth.feature.result.components.SnowHeightCard
import com.example.icebeth.feature.result.components.SnowHeightCountCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    state: ResultState,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Снегосъемка: ${state.result.time.formatDateFromTimestamp()}")
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            SnowHeightCard(state)

            SnowHeightCountCard(state)
        }
    }
}
