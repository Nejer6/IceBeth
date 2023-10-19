package com.example.icebeth.common.util

/**
 * This function performs word inflection based on a given number.
 *
 * @param word The base word (e.g., "Съем".
 * @param n The number for which inflection is to be performed.
 * @param form1 Form of the word for number 1 (e.g., "ка").
 * @param form2 Form of the word for numbers 2, 3, 4 (e.g., "ки").
 * @param form3 Form of the word for numbers 0, 5-20 (e.g., "ок").
 *
 * @return The inflected word based on the given number.
 */
fun getCorrectEnding(word: String, n: Int, form1: String, form2: String, form3: String): String {
    return when {
        n % 100 in 10..19 || n % 10 > 4 || n % 10 == 0 -> word + form3
        n % 10 == 1 -> word + form1
        else -> word + form2
    }
}