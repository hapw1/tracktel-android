package com.example.track_tel_android.data

import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    suspend fun insertTrack(track: Track)
    suspend fun insertAllTracks(tracks: List<Track>)
    suspend fun deleteTrack(track: Track)
    suspend fun deleteAllTracks()
    fun getTrack(track: Track): Track
    fun getAllTracks(): Flow<List<Track>>
}