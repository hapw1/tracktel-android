package com.example.track_tel_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

//import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "track_table")
data class Track(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val county: String,
    val length: Float,
    val turns: Int,
    val distanceFromUser: Double,
    //val location: LatLng,
    val finishLineLeftLat: Double,
    val finishLineLeftLong: Double,
    val finishLineRightLat: Double,
    val finishLineRightLong: Double,
    val geofenceLat: Double,
    val geofenceLong: Double,
    //val startLineTrigger: Int,
    val isCircuit: Boolean,
    //val finishLineLocation: LatLng? =  null,
    //val finishLineTrigger: Int,
    val bestLap: Long,
    val trackImagePath: String
)
