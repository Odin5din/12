package com.example.myapplication.Utils

import android.content.Context
import android.net.ParseException
import android.util.Log
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.Date


object Utils {

    fun getDateFromTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?): String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        Log.d("date", "getDate >> $date")

        val df = SimpleDateFormat(toFormatformat)
        res = df.format(date)
        return res
    }

    fun saveLastSearch(context: Context, query: String) {
        val prefs = context.getSharedPreferences("", Context.MODE_PRIVATE)
        prefs.edit().putString("", query)
    }

    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences("", Context.MODE_PRIVATE)
        return prefs.getString("", null)
    }

}