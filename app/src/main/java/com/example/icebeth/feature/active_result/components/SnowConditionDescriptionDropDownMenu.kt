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
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.domain.util.ResultError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnowConditionDescriptionDropDownMenu(
    snowConditionDescription: SnowConditionDescription?,
    onSnowConditionDescriptionChange: (SnowConditionDescription) -> Unit,
    snowConditionDescriptionError: ResultError?,
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
            value = snowConditionDescription?.description ?: "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            label = {
                Text(text = "Характеристика состояния снега")
            },
            isError = snowConditionDescriptionError != null,
            supportingText = {
                if (snowConditionDescriptionError != null) {
                    Text(text = when (snowConditionDescriptionError) {
                        ResultError.Empty -> "Выберите значение"
                        else -> ""
                    })
                }
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider()

            SnowConditionDescription.entries.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.description) },
                    onClick = {
                        onSnowConditionDescriptionChange(it)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()
            }
        }
    }
}