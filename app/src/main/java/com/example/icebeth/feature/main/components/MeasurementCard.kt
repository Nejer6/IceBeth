package com.example.icebeth.feature.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.icebeth.core.model.Measurement
import com.example.icebeth.features.measurements.presentation.components.MyCard

@Composable
fun MeasurementCard(
    index: Int,
    item: Measurement,
    navigate: (Int, Measurement) -> Unit,
    onDelete: (Int) -> Unit
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
            navigate(item.resultId, item)
        }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Редактировать"
            )
        }

        IconButton(onClick = { onDelete(item.id) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}