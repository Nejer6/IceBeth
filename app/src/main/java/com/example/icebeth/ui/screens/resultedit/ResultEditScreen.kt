package com.example.icebeth.ui.screens.resultedit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.ui.components.ResultEditor
import com.example.icebeth.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultEditScreen(
    navigateUp: () -> Unit,
    state: ResultEditState,
    onEvent: (ResultEditEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Редактирование") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Отменить")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ResultEditEvent.Save) }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Сохранить")
            }
        }
    ) { paddingValues ->
        ResultEditor(
            degreeOfCoverage = state.degreeOfCoverage,
            snowCoverCharacter = state.snowCoverCharacter,
            snowConditionDescription = state.snowConditionDescription,
            onDegreeOfCoverageChange = { onEvent(ResultEditEvent.ChangeDegreeOfCoverage(it)) },
            onSnowCoverCharacterChange = { onEvent(ResultEditEvent.ChangeSnowCoverCharacter(it)) },
            onSnowConditionDescriptionChange = {
                onEvent(ResultEditEvent.ChangeSnowConditionDescription(it))
            },
            degreeOfCoverageError = state.degreeOfCoverageError,
            snowCoverCharacterError = null,
            snowConditionDescriptionError = null,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium)
        )
    }
}
