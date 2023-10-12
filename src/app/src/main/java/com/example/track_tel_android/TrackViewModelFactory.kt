package com.example.track_tel_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.track_tel_android.data.TrackRepository

class TrackViewModelFactory(private val repository: TrackRepository) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T = TrackViewModel(repository) as T
}