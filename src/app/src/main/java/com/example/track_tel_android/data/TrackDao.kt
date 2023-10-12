package com.example.track_tel_android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert
    suspend fun insertTrack(track: Track)

    @Insert
    suspend fun insertAllTracks(tracks: List<Track>)

    @Delete
    fun deleteTrack(track: Track)

    @Query("DELETE FROM track_table")
    suspend fun deleteAllTracks()

    @Query("SELECT * FROM track_table WHERE name = :name")
    fun getTrack(name: String): Track

    @Query("SELECT * FROM track_table")
    fun getAllTracks(): Flow<List<Track>>

}