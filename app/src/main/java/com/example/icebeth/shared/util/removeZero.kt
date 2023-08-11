package com.example.icebeth.shared.util

fun Float.removeZero(): String {
    val str = this.toString()
    return if (str.endsWith(".0")) {
        str.removeSuffix(".0")
    } else {
        str
    }
}