package com.example.icebeth.feature.activeresult.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.domain.util.ResultError

@Composable
fun SnowCoverCharacterDropDownMenu(
    snowCoverCharacter: SnowCoverCharacter?,
    onSnowCoverCharacterChange: (SnowCoverCharacter) -> Unit,
    snowCoverCharacterError: ResultError?,
    modifier: Modifier = Modifier
) {
    DropDownMenu(
        selectedItem = snowCoverCharacter,
        onItemChange = onSnowCoverCharacterChange,
        isError = snowCoverCharacterError != null,
        label = "Характер залегания снежного покрова",
        items = SnowCoverCharacter.entries,
        modifier = modifier
    )
}
