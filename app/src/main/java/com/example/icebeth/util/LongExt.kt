package com.example.icebeth.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats a timestamp into a string representation using a custom pattern.
 *
 * @param pattern The pattern specifying the format (e.g., "dd.MM.yyyy HH:mm").
 *
 * @return A formatted date and time string based on the provided pattern.
 */
fun Long.formatDateFromTimestamp(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val date = Date(this)
    return dateFormat.format(date)
}

/**
 * Formats a timestamp into a date and time string (e.g., "dd.MM.yyyy HH:mm").
 *
 * @return A formatted date and time string.
 */
fun Long.formatDateWithTimeFromTimestamp() =
    this.formatDateFromTimestamp("dd.MM.yyyy HH:mm")

/**
 * Formats a timestamp into a time string (e.g., "HH:mm").
 *
 * @return A formatted time string.
 */
fun Long.formatTimeFromTimestamp() =
    this.formatDateFromTimestamp("HH:mm")

/**
 * Formats a timestamp into a date string (e.g., "dd.MM.yyyy").
 *
 * @return A formatted date string.
 */
fun Long.formatDateFromTimestamp() =
    this.formatDateFromTimestamp("dd.MM.yyyy")
