package com.example.icebeth.ui.screens.activeresult.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition
import com.example.icebeth.core.domain.util.MeasurementError
import com.example.icebeth.ui.components.DropDownMenu

@Composable
fun SoilSurfaceConditionDropDownMenu(
    soilSurfaceCondition: SoilSurfaceCondition?,
    onSoilSurfaceConditionChange: (SoilSurfaceCondition) -> Unit,
    soilSurfaceConditionError: MeasurementError?,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false
) {
    DropDownMenu(
        selectedItem = soilSurfaceCondition,
        onItemChange = onSoilSurfaceConditionChange,
        isError = soilSurfaceConditionError != null,
        label = "Состояние поверхности почвы",
        items = SoilSurfaceCondition.entries,
        modifier = modifier,
        readOnly = readOnly
    )
}
