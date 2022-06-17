package com.example.test.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun ConvertDateTimeFormat(strDate: String?): String {
    val Date: String
    if (strDate != null) {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat: DateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.getDefault())
        var date: Date? = null
        try {
            date = inputFormat.parse(strDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        assert(date != null)
        Date = outputFormat.format(date)
    } else {
        Date = "--:--"
    }
    return Date
}
