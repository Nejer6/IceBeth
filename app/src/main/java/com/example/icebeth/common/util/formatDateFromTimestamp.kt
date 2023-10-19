package com.example.icebeth.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats a timestamp into a string representation using a custom pattern.
 *
 * @param timestamp The timestamp to be formatted.
 * @param pattern The pattern specifying the format (e.g., "dd.MM.yyyy HH:mm").
 *
 * @return A formatted date and time string based on the provided pattern.
 */
fun formatDateFromTimestamp(timestamp: Long, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}

/**
 * Formats a timestamp into a date and time string (e.g., "dd.MM.yyyy HH:mm").
 *
 * @param timestamp The timestamp to be formatted.
 *
 * @return A formatted date and time string.
 */
fun formatDateWithTimeFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "dd.MM.yyyy HH:mm")

/**
 * Formats a timestamp into a time string (e.g., "HH:mm").
 *
 * @param timestamp The timestamp to be formatted.
 *
 * @return A formatted time string.
 */
fun formatTimeFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "HH:mm")

/**
 * Formats a timestamp into a date string (e.g., "dd.MM.yyyy").
 *
 * @param timestamp The timestamp to be formatted.
 *
 * @return A formatted date string.
 */
fun formatDateFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "dd.MM.yyyy")