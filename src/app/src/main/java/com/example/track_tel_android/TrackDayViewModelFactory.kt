package com.example.track_tel_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.track_tel_android.data.trackday.TrackDayRepository

class TrackDayViewModelFactory(private val repository: TrackDayRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T = TrackDayViewModel(repository) as T

}