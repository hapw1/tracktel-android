package com.example.track_tel_android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.data.trackday.TrackDayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTrackDayViewModel(private val repository: TrackDayRepository) : ViewModel() {

    fun addTrackDay(trackDay: TrackDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTrackDay(trackDay)
        }
    }
}