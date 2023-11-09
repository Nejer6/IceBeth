package com.example.icebeth.feature.result.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.feature.result.ResultState

@Composable
fun SnowHeightCard(state: ResultState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            SplitText(
                text1 = "Средняя высота снега: ",
                text2 = String.format("%.1f", state.result.averageSnowHeight),
                Modifier.fillMaxWidth()
            )
            SplitText(
                text1 = "Минимальная высота снега: ",
                text2 = state.result.minSnowHeight.toString(),
                Modifier.fillMaxWidth()
            )
            SplitText(
                text1 = "Максимальная высота снега: ",
                text2 = state.result.maxSnowHeight.toString(),
                Modifier.fillMaxWidth()
            )
            SplitText(
                text1 = "Сумма высот снега: ",
                text2 = state.result.sumOfSnowHeights.toString(),
                Modifier.fillMaxWidth()
            )
        }
    }
}
