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
fun SnowHeightCountCard(state: ResultState) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            SplitText(
                text1 = "Высот снега от 1 до 3:",
                text2 = state.result.sum13.toString(),
                modifier = Modifier.fillMaxWidth()
            )

            SplitText(
                text1 = "Высот снега от 4 до 6:",
                text2 = state.result.sum46.toString(),
                modifier = Modifier.fillMaxWidth()
            )

            SplitText(
                text1 = "Высот снега долее 30:",
                text2 = state.result.heightGreaterThan30.toString(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
