package com.example.icebeth.core.model

import com.example.icebeth.core.data.database.model.ResultEntity
import java.util.Date

data class Result(
    val id: Int = 0,
    val time: Long = Date().time,
    val isActive: Boolean = true
)

fun Result.asEntity() = ResultEntity(
    id = id,
    time = time,
    isActive = isActive
)
