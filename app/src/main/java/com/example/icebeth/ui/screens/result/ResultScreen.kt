package com.example.icebeth.ui.screens.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.icebeth.ui.screens.result.components.DensityAndWaterCard
import com.example.icebeth.ui.screens.result.components.MainResultCard
import com.example.icebeth.ui.screens.result.components.SnowHeightCard
import com.example.icebeth.ui.screens.result.components.SnowHeightCountCard
import com.example.icebeth.ui.theme.spacing
import com.example.icebeth.util.formatDateFromTimestamp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    state: ResultState,
    navigateUp: () -> Unit,
    navigateToMeasurements: (Int) -> Unit,
    navigateToResultEdit: (Int) -> Unit
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
                },
                actions = {
                    IconButton(onClick = { navigateToResultEdit(state.result.id) }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Редактировать")
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
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainResultCard(state)

            SnowHeightCard(state)

            SnowHeightCountCard(state)

            DensityAndWaterCard(state)

            OutlinedButton(onClick = { navigateToMeasurements(state.result.id) }) {
                Text(text = "Измерения")
            }
        }
    }
}
