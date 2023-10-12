package com.example.track_tel_android.util

import android.os.Looper
import com.example.track_tel_android.currentLapTime
import java.sql.Timestamp
import java.util.concurrent.TimeUnit

class Stopwatch {

    val mInterval = 1L
    var mHandler: android.os.Handler? = null

    var startTime: Long = 0L
    var currentTime: Long = 0L

    private var mStatusChecker: Runnable = object : Runnable{
        override fun run() {
            try{
                //timeInMilliseconds += 10
                //totalTimeInMilliseconds += 10
                //currentLapTime = timeInMilliseconds
                currentTime = System.currentTimeMillis()
                timeInMilliseconds = currentTime - startTime
                currentLapTime = timeInMilliseconds
            }finally {
                mHandler!!.postDelayed(this, mInterval)
            }
        }
    }

    var timeInMilliseconds = 0L
    var totalTimeInMilliseconds = 0L
    var averageTimeInMilliseconds = 0L
    var bestTimeInMilliseconds = 0L

    var lapCount = 0

    fun startStopwatch(){
        startTime = System.currentTimeMillis()
        mHandler = android.os.Handler(Looper.getMainLooper())
        mStatusChecker.run()
    }

    fun stopStopwatch(){
        lapCount += 1
        totalTimeInMilliseconds += timeInMilliseconds
        mHandler?.removeCallbacks(mStatusChecker)
    }

    fun resetStopWatch(){
        currentLapTime = 0L
        timeInMilliseconds = 0L
    }

    fun calculateBestLap(): Long{
        if ((bestTimeInMilliseconds != 0L && (bestTimeInMilliseconds > timeInMilliseconds))){
            bestTimeInMilliseconds = timeInMilliseconds
        }else if(bestTimeInMilliseconds == 0L){
            bestTimeInMilliseconds = timeInMilliseconds
        }
        return bestTimeInMilliseconds
    }

    fun calculateAverageLap(): Long{
        averageTimeInMilliseconds = (totalTimeInMilliseconds / lapCount) - 1
        return averageTimeInMilliseconds
    }


}