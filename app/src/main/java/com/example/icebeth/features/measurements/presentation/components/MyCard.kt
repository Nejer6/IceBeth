package com.example.icebeth.features.measurements.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.icebeth.shared.presentation.theme.spacing

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyCard(
    title: String,
    stats: List<Pair<String, Float>> = listOf(),
    statsWithTitles: List<Pair<String, List<Pair<String, Float>>>> = listOf(),
    status: List<String> = listOf(),
    actions: @Composable ColumnScope.() -> Unit
) {
    var isCardOpen by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(
        targetValue = if (isCardOpen) 90f else 0f, label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        onClick = { isCardOpen = !isCardOpen }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = title
            )

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.graphicsLayer { rotationZ = rotation }
            )
        }

        if (isCardOpen) {
            Divider(
                color = MaterialTheme.colorScheme.outline
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    stats.forEach {
                        TextWithNumber(text = it.first, float = it.second)
                    }

                    if (statsWithTitles.isNotEmpty() && stats.isNotEmpty())
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                    statsWithTitles.forEach {
                        Column {
                            Text(
                                text = it.first,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Column {
                                it.second.forEach {
                                    TextWithNumber(text = it.first, float = it.second)
                                }
                            }
                        }
                    }

                    if (status.isNotEmpty())
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                    status.forEach {
                        Text(text = it)
                    }

                }

                Column(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall),
                    content = actions
                )
            }
        }
    }
}