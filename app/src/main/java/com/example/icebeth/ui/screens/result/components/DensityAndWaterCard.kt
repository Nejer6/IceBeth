package com.example.icebeth.ui.screens.result.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.ui.screens.result.ResultState

@Composable
fun DensityAndWaterCard(
    state: ResultState,
    modifier: Modifier = Modifier
) {
    CardWithColumn(
        items = listOf(
            "Плотность снега: " to String.format("%.7f", state.result.density),
            "Общий запас воды: " to String.format("%.1f", state.result.totalWaterSupply)
        ),
        modifier = modifier
    )
}
