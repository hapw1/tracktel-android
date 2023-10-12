package com.example.track_tel_android.data.trackday

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "track_day_table")
data class TrackDay(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var dateTime: LocalDateTime,
    var lapCount: Int,
    var bestLap: Long,
    var averageLap: Long,
    var vMax: Float,
    var laps: List<Long>
)
