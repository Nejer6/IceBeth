package com.example.icebeth.core.model.data

data class Measurement(
    val cylinderHeight: Float,
    val groundFrozzed: Boolean,
    val id: Int,
    val massOfSnow: Float,
    val snowCrust: Boolean,
    val snowHeight: Float,
    val resultId: Int,
    val time: Long,
    val isUploaded: Boolean
)
