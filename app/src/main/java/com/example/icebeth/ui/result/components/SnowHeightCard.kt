package com.example.icebeth.ui.result.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.ui.result.ResultState

@Composable
fun SnowHeightCard(
    state: ResultState,
    modifier: Modifier = Modifier
) {
    CardWithColumn(
        modifier = modifier,
        items = listOf(
            "Средняя высота снега: " to String.format("%.1f", state.result.averageSnowHeight),
            "Минимальная высота снега: " to state.result.minSnowHeight.toString(),
            "Максимальная высота снега: " to state.result.maxSnowHeight.toString(),
            "Сумма высот снега: " to state.result.sumOfSnowHeights.toString()
        )
    )
}
