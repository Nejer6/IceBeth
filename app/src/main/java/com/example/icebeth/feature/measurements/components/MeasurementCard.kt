package com.example.icebeth.feature.measurements.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.icebeth.common.presentation.util.AppRoute
import com.example.icebeth.core.model.data.Measurement
import com.example.icebeth.features.measurements.presentation.components.MyCard
import com.example.icebeth.feature.measurements.MeasurementsEvent
import com.example.icebeth.feature.measurements.MeasurementsState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MeasurementCard(
    state: MeasurementsState,
    index: Int,
    item: Measurement,
    navigate: (String) -> Unit,
    onEvent: (MeasurementsEvent) -> Unit
) {
    MyCard(
        title = "Замер №${index}",
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

        IconButton(onClick = { onEvent(MeasurementsEvent.Delete(item.id)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}