package com.example.track_tel_android.data.trackday

import com.example.track_tel_android.data.Track
import kotlinx.coroutines.flow.Flow

interface TrackDayRepository {

    suspend fun insertTrackDay(trackDay: TrackDay)
    suspend fun insertAllTrackDays(trackDays: List<TrackDay>)
    suspend fun deleteTrackDay(trackDay: TrackDay)
    suspend fun deleteAllTrackDays()
    fun getTrackDay(trackDay: TrackDay): TrackDay
    fun getAllTrackDays(): Flow<List<TrackDay>>
}