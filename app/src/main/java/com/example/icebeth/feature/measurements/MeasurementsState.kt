package com.example.icebeth.feature.measurements

import androidx.compose.runtime.Stable
import com.example.icebeth.core.data.database.model.MeasurementEntity

@Stable
data class MeasurementsState(
    val measurements: List<MeasurementEntity> = emptyList()
)
