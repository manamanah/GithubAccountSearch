package com.example.android.githubaccountsearch.network

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarAdapter : JsonAdapter<Calendar>() {

    private val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    private val calendar by lazy { Calendar.getInstance() }

    @FromJson
    override fun fromJson(reader: JsonReader): Calendar? {
        synchronized(this) {
            return try {
                val dateString = reader.nextString()
                val date = dateFormat.parse(dateString) ?: calendar.time
                calendar.time = date
                return calendar
            } catch (e: Exception) {
                Log.e(this.javaClass.simpleName, "Exception parsing date: $e")
                null
            }
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Calendar?) {
        throw NotImplementedError()
    }
}
