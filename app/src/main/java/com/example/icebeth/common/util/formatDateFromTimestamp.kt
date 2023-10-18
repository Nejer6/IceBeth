package com.example.icebeth.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateFromTimestamp(timestamp: Long, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}

fun formatDateWithTimeFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "dd.MM.yyyy HH:mm")

fun formatTimeFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "HH:mm")

fun formatDateFromTimestamp(timestamp: Long) =
    formatDateFromTimestamp(timestamp, "dd.MM.yyyy")