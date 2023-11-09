package com.example.icebeth.feature.archive

import androidx.compose.runtime.Stable
import com.example.icebeth.core.data.database.model.ResultEntity

@Stable
data class ArchiveState(
    val results: List<ResultEntity> = emptyList()
)
