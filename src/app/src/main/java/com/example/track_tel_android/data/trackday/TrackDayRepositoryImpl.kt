package com.example.track_tel_android.data.trackday

import kotlinx.coroutines.flow.Flow

class TrackDayRepositoryImpl(private val dao: TrackDayDao): TrackDayRepository {
    override suspend fun insertTrackDay(trackDay: TrackDay) {
        dao.insertTrackDay(trackDay)
    }

    override suspend fun insertAllTrackDays(trackDays: List<TrackDay>) {
        dao.insertAllTrackDays(trackDays)
    }

    override suspend fun deleteTrackDay(trackDay: TrackDay) {
        dao.deleteTrackDay(trackDay)
    }

    override suspend fun deleteAllTrackDays() {
        dao.deleteAllTrackDays()
    }

    override fun getTrackDay(trackDay: TrackDay): TrackDay {
        return dao.getTrackDay(trackDay.name)
    }

    override fun getAllTrackDays(): Flow<List<TrackDay>> {
        return dao.getAllTrackDays()
    }


}