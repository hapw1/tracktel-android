package com.example.track_tel_android

import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

fun formatLapTime(lapTime: Long): String{

    var milliseconds = lapTime
    //var minutes = (Math.floor(lapTime / 60).toInt())
    //var seconds = String.format("%.3f", (lapTime % 60)).padStart(2, '0')

    var minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
    milliseconds -= TimeUnit.MINUTES.toMillis(minutes)

    var seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
    milliseconds -= TimeUnit.SECONDS.toMillis(seconds)

    return "${minutes.toString().padStart(2, '0')}:" +
            "${seconds.toString().padStart(2, '0')}." +
            "${milliseconds.toString().padStart(3, '0')}"
}

fun formatDateTime(dateTime: LocalDateTime): String{
    var dateTimeFormat = "dd/MM/yy hh:mm"
    var simpleDateFormat = DateTimeFormatter.ofPattern(dateTimeFormat)
    return dateTime.format(simpleDateFormat)
}
