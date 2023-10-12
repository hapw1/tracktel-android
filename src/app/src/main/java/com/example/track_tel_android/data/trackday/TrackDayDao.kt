package com.example.track_tel_android.data.trackday

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.track_tel_android.data.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDayDao {
    @Insert
    suspend fun insertTrackDay(trackDay: TrackDay)

    @Insert
    suspend fun insertAllTrackDays(trackdays: List<TrackDay>)

    @Delete
    fun deleteTrackDay(trackday: TrackDay)

    @Query("DELETE FROM track_day_table")
    suspend fun deleteAllTrackDays()

    @Query("SELECT * FROM track_day_table WHERE name = :name")
    fun getTrackDay(name: String): TrackDay

    @Query("SELECT * FROM track_day_table")
    fun getAllTrackDays(): Flow<List<TrackDay>>
}