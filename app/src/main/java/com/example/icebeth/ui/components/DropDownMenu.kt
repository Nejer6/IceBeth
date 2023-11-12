package com.example.icebeth.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.icebeth.core.data.database.model.Description

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Description> DropDownMenu(
    selectedItem: T?,
    onItemChange: (T) -> Unit,
    isError: Boolean,
    label: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    key(readOnly) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (!readOnly) expanded = !expanded
            },
            modifier = modifier
        ) {
            TextField(
                value = selectedItem?.description ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                label = {
                    Text(text = label)
                },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(text = "Выберите значение")
                    }
                }
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                Divider()

                items.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it.description)
                        },
                        onClick = {
                            onItemChange(it)
                            expanded = false
                        }
                    )

                    Divider()
                }
            }
        }
    }
}
