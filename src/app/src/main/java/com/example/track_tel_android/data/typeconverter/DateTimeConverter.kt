package com.example.track_tel_android.data.typeconverter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object DateTimeConverter {

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(timeStamp: Long): LocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timeStamp),
        TimeZone.getDefault().toZoneId()
    )

    @TypeConverter
    @JvmStatic
    fun toTimeStamp(localDateTime: LocalDateTime): Long =
        localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}