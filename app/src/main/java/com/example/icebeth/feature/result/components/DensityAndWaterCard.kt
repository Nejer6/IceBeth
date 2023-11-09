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
fun DensityAndWaterCard(state: ResultState) {
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
                text1 = "Плотность снега: ",
                text2 = String.format("%.7f", state.result.density),
                Modifier.fillMaxWidth()
            )
            SplitText(
                text1 = "Общий запас воды: ",
                text2 = String.format("%.1f", state.result.totalWaterSupply),
                Modifier.fillMaxWidth()
            )
        }
    }
}
