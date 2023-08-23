package com.example.icebeth.features.measurements.presentation.results.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.icebeth.features.measurements.data.remote.response.Result
import com.example.icebeth.features.measurements.presentation.components.MyCard
import com.example.icebeth.features.measurements.presentation.results.ResultsEvent
import com.example.icebeth.shared.presentation.util.AppRoute

@Composable
fun ResultCard(
    it: Result,
    navigate: (String) -> Unit,
    onEvent: (ResultsEvent) -> Unit
) {
    MyCard(
        title = "Съемка №${it.id}",
        statsWithTitles = listOf(
            "Высота снега" to listOf(
                "Средняя" to it.averageSnowHeight,
                "Максимальная" to it.maxSnowHeight,
                "Минимальная" to it.minSnowHeight
            )
        )
    ) {
        IconButton(onClick = {
            navigate(
                "${AppRoute.MeasurementsScreen.route}/${it.id}"
            )
        }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Редактировать"
            )
        }

        IconButton(onClick = { onEvent(ResultsEvent.Delete(it.id)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}