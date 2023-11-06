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
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoilSurfaceConditionDropDownMenu(
    soilSurfaceCondition: SoilSurfaceCondition?,
    onSoilSurfaceConditionChange: (SoilSurfaceCondition) -> Unit,
    soilSurfaceConditionError: MeasurementError?,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (!readOnly) {
                expanded = !expanded
            }
        },
        modifier = modifier
    ) {
        TextField(
            value = soilSurfaceCondition?.description ?: "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            label = {
                Text(text = "Состояние поверхности почвы")
            },
            isError = soilSurfaceConditionError != null,
            supportingText = {
                if (soilSurfaceConditionError != null) {
                    Text(
                        text = when (soilSurfaceConditionError) {
                            MeasurementError.Empty -> "Выберите значение"
                            else -> ""
                        }
                    )
                }
            }
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Divider()

            SoilSurfaceCondition.entries.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.description) },
                    onClick = {
                        onSoilSurfaceConditionChange(it)
                        expanded = false
                    },
                )

                Divider()
            }
        }
    }
}