package com.example.icebeth.feature.result.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun SplitText(
    text1: String,
    text2: String,
    modifier: Modifier = Modifier,
    text1Style: TextStyle = LocalTextStyle.current
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text1, style = text1Style)
        Text(text = text2)
    }
}
