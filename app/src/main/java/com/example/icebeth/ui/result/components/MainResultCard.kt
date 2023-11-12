package com.example.icebeth.ui.result.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.ui.result.ResultState

@Composable
fun MainResultCard(state: ResultState) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SplitText(
            text1 = "Степень покрытия в баллах: ",
            text2 = state.result.degreeOfCoverage.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
                .padding(
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.small
                ),
            text1Style = MaterialTheme.typography.titleMedium
        )

        Divider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                )
        ) {
            Text(
                text = "Характер залегания снежного покрова: ",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = state.result.snowCoverCharacter.description)
        }

        Divider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
                .padding(
                    bottom = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.small
                )
        ) {
            Text(
                text = "Характеристика состояния снега: ",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = state.result.snowConditionDescription.description)
        }
    }
}
