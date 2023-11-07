package com.example.icebeth.feature.activeresult.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.domain.util.ResultError

@Composable
fun SnowConditionDescriptionDropDownMenu(
    snowConditionDescription: SnowConditionDescription?,
    onSnowConditionDescriptionChange: (SnowConditionDescription) -> Unit,
    snowConditionDescriptionError: ResultError?,
    modifier: Modifier = Modifier
) {
    DropDownMenu(
        selectedItem = snowConditionDescription,
        onItemChange = onSnowConditionDescriptionChange,
        isError = snowConditionDescriptionError != null,
        label = "Характеристика состояния снега",
        items = SnowConditionDescription.entries,
        modifier = modifier
    )
}
