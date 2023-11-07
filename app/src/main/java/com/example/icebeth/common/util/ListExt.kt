package com.example.icebeth.common.util

/**
 * Calculates the average value of elements in a list by applying a custom transformation.
 *
 * @param block A lambda that defines how to transform elements of the list into floating-points values.
 *
 * @return The average value of the transformed elements in the list as a floating-point number.
 */
fun <T> List<T>.average(block: (T) -> Int): Double {
    return this.sumOf(block).toDouble() / this.size
}
