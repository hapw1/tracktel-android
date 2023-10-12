package com.example.track_tel_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.data.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackViewModel(private val repository: TrackRepository): ViewModel() {

    val trackList = repository.getAllTracks()

    fun addTrack(track: Track){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTrack(track)
        }
    }

    fun addAllTracks(tracks: List<Track>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllTracks(tracks)
        }
    }

    fun deleteTrack(track: Track){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTrack(track)
        }
    }
}