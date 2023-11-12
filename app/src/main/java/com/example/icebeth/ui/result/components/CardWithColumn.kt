package com.example.icebeth.ui.result.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icebeth.common.presentation.theme.spacing

@Composable
fun CardWithColumn(
    items: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            items.forEach {
                SplitText(
                    text1 = it.first,
                    text2 = it.second,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
