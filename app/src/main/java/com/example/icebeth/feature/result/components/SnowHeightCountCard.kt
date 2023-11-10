package com.example.icebeth.feature.result.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.feature.result.ResultState

@Composable
fun SnowHeightCountCard(
    state: ResultState,
    modifier: Modifier = Modifier
) {
    CardWithColumn(
        modifier = modifier,
        items = listOf(
            "Высот снега от 1 до 3: " to state.result.sum13.toString(),
            "Высот снега от 4 до 6: " to state.result.sum46.toString(),
            "Высот снега долее 30: " to state.result.heightGreaterThan30.toString()
        )
    )
}
