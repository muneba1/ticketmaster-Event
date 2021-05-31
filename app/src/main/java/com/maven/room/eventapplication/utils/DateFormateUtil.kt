package com.maven.room.eventapplication.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class DateFormatUtil {

    fun dateFormat(date: String,dateFormat: String = "yyyy-MM-dd", timeZone: TimeZone = TimeZone.getTimeZone("UTC")):Date{
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(date)
    }
}