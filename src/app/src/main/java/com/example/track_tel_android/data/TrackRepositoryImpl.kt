package com.example.track_tel_android.data

import kotlinx.coroutines.flow.Flow

class TrackRepositoryImpl(private val dao: TrackDao) : TrackRepository {

    override suspend fun insertTrack(track: Track) {
        dao.insertTrack(track)
    }

    override suspend fun insertAllTracks(tracks: List<Track>) {
        dao.insertAllTracks(tracks)
    }

    override suspend fun deleteTrack(track: Track) {
        dao.deleteTrack(track)
    }

    override suspend fun deleteAllTracks() {
        dao.deleteAllTracks()
    }

    override fun getTrack(track: Track): Track {
        return dao.getTrack(track.name)
    }

    override fun getAllTracks(): Flow<List<Track>> {
        return dao.getAllTracks()
    }
}