package com.example.icebeth.common.util

/**
 * Calculates the average value of elements in a list by applying a custom transformation.
 *
 * @param block A lambda that defines how to transform elements of the list into floating-points values.
 *
 * @return The average value of the transformed elements in the list as a floating-point number.
 */
fun <T> List<T>.average(block: (T) -> Float): Float {
    // Calculate the sum of the transformed elements using the provided lambda.
    val sum = this.fold(0f) { acc, t ->
        acc + block(t)
    }

    // Calculate the average by dividing the sum by the number of elements in the list.
    return sum / this.size
}