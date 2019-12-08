package com.hing.newsapplication.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface DateTimeHelper {
    fun getDateString(publishedAt: String): String
}

class DateTimeHelperImpl @Inject constructor() : DateTimeHelper {
    override fun getDateString(publishedAt: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val result = format.parse(publishedAt)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.format(result).toString()
    }
}
