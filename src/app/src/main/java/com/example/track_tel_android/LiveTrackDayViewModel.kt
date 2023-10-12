package com.example.track_tel_android

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.track_tel_android.data.TimingLine
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.data.trackday.TrackDayDatabase
import com.example.track_tel_android.data.trackday.TrackDayRepositoryImpl
import com.example.track_tel_android.util.Stopwatch
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

private var nearFinish: Boolean = false
var currentLapTime by mutableStateOf(0L)


class LiveTrackDayViewModel(application: Application) : AndroidViewModel(application) {



    var sessionInProgress by mutableStateOf(false)

    var trackDayLocation by mutableStateOf("")
    var trackDayDate by mutableStateOf("")

    var lapCount by mutableStateOf(1)

    var bestLap by mutableStateOf(0L)
    var averageLap by mutableStateOf(0L)

    var laps = mutableStateListOf<Long>()

    var stopwatch = Stopwatch()

    fun startSession(){
        stopwatch.startStopwatch()
        sessionInProgress = true
    }


    fun endSession(){
        stopwatch.stopStopwatch()
        stopwatch.resetStopWatch()
        sessionInProgress = false
        //saveTrackDay()
    }


    fun newLap(){
        stopwatch.stopStopwatch()
        bestLap = stopwatch.calculateBestLap()
        averageLap = stopwatch.calculateAverageLap()
        lapCount += 1
        laps.add(currentLapTime)
        stopwatch.resetStopWatch()
        stopwatch.startStopwatch()
    }



    private fun hasCrossedTimingLine(
        timingLine: TimingLine,
        lastLocation: LatLng,
        currentLocation: LatLng): Boolean{

        var fDeltaLat: Double = timingLine.rightLatLng.latitude - timingLine.leftLatLng.latitude
        var fDeltaLong: Double = timingLine.rightLatLng.longitude - timingLine.leftLatLng.longitude

        var da: Double = currentLocation.latitude - lastLocation.latitude
        var db: Double = currentLocation.longitude - lastLocation.longitude

        if(((da*fDeltaLong) - (db*fDeltaLat)) == 0.0){
            return false
        }

        var s =
            (fDeltaLat*(lastLocation.longitude - timingLine.leftLatLng.longitude) +
                    (fDeltaLong*(timingLine.leftLatLng.latitude - lastLocation.latitude))/
                    ((da*fDeltaLong) - (db*fDeltaLat)))
        var t = (da*(timingLine.leftLatLng.longitude - lastLocation.longitude) +
                (db*(lastLocation.latitude - timingLine.leftLatLng.latitude)) /
                ((db*fDeltaLat - (db*fDeltaLong))))

        var lineCrossed = ((s >= 0 && s <= 1) && (t >= 0 && t <= 1))

        if(lineCrossed){
        }
        return lineCrossed

    }


}