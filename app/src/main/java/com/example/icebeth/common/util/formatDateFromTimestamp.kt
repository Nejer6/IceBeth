package com.example.icebeth.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateFromTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}