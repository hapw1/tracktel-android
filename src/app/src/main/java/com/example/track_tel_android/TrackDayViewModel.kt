package com.example.track_tel_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.data.trackday.TrackDayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackDayViewModel(private val repository: TrackDayRepository) : ViewModel() {

    val trackDayList = repository.getAllTrackDays()

    fun addTrackDay(trackDay: TrackDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTrackDay(trackDay)
        }
    }

    fun addAllTrackDays(trackDays: List<TrackDay>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllTrackDays(trackDays)
        }
    }

    fun deleteTrackDay(trackDay: TrackDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTrackDay(trackDay)
        }
    }
}