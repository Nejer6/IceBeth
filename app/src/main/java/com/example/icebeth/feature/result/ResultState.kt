package com.example.icebeth.feature.result

import androidx.compose.runtime.Stable
import com.example.icebeth.core.data.database.model.ResultEntity

@Stable
data class ResultState(
    val resultEntity: ResultEntity
)
