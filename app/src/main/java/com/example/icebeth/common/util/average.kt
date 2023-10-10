package com.example.icebeth.common.util

fun <T> List<T>.average(block: (T) -> Float): Float {
    val sum = this.fold(0f) { acc, t ->
        acc + block(t)
    }
    return sum / this.size
}