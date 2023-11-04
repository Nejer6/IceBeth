package com.example.icebeth.feature.active_result.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.domain.util.ResultError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnowCoverCharacterDropDownMenu(
    snowCoverCharacter: SnowCoverCharacter?,
    onSnowCoverCharacterChange: (SnowCoverCharacter) -> Unit,
    snowCoverCharacterError: ResultError?,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = snowCoverCharacter?.description ?: "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            label = {
                Text(text = "Характер залегания снежного покрова")
            },
            isError = snowCoverCharacterError != null,
            supportingText = {
                if (snowCoverCharacterError != null) {
                    Text(text = when (snowCoverCharacterError) {
                        ResultError.Empty -> "Выберите значение"
                        else -> ""
                    })
                }
            }
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Divider()

            SnowCoverCharacter.entries.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.description) },
                    onClick = {
                        onSnowCoverCharacterChange(it)
                        expanded = false
                    },
                )

                Divider()
            }
        }
    }
}