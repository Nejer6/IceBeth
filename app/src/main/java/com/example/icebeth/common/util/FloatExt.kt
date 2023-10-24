package com.example.icebeth.common.util

/**
 * Converts a floating-point number to a string, removing trailing ".0" if present.
 *
 * @return A string representation of the floating-point number with trailing ".0" removed.
 */
fun Float.removeZero(): String {
    val str = this.toString()
    return if (str.endsWith(".0")) {
        str.removeSuffix(".0")
    } else {
        str
    }
}