package com.example.icebeth.features.measurements.presentation.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.icebeth.features.measurements.data.remote.response.Measurement
import com.example.icebeth.features.measurements.presentation.components.MyCard
import com.example.icebeth.features.measurements.presentation.main.MainEvent
import com.example.icebeth.features.measurements.presentation.main.MainState
import com.example.icebeth.shared.presentation.util.AppRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MeasurementCard(
    state: MainState,
    index: Int,
    item: Measurement,
    navigate: (String) -> Unit,
    onEvent: (MainEvent) -> Unit
) {
    MyCard(
        title = "Замер №${state.measurements.size - index}",
        stats = listOf(
            "Масса снега" to item.massOfSnow,
            "Высота снега" to item.snowHeight,
            "Высота цилиндра" to item.cylinderHeight
        ),
        status = mutableListOf<String>().apply {
            if (item.snowCrust) add("Снежная корка")
            if (item.groundFrozzed) add("Земля промерзла")
        }
    ) {
        IconButton(onClick = {
            navigate(
                "${AppRoute.AddMeasurementScreen.route}/${state.resultId}?measurement=${
                    Json.encodeToString(item)
                }"
            )
        }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Редактировать"
            )
        }

        IconButton(onClick = { onEvent(MainEvent.Delete(item.id)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}