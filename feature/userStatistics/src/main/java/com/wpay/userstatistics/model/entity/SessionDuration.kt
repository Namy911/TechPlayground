package com.wpay.userstatistics.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "session_durations")
data class SessionDuration(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val screenName: String,
    val duration: Long,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return dateFormat.format(Date(timestamp))
    }

    fun getFormattedTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
        return timeFormat.format(Date(timestamp))
    }

    override fun toString(): String {
        return "SessionDuration(id=$id, userId='$userId', duration=$duration, timestamp=$timestamp)"
    }
}