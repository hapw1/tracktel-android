package com.example.track_tel_android.compose.history

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.track_tel_android.TrackDayViewModel
import com.example.track_tel_android.TrackDayViewModelFactory
import com.example.track_tel_android.TrackViewModel
import com.example.track_tel_android.TrackViewModelFactory
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.ui.theme.*
import java.time.LocalDate

@Preview(showBackground = true)
@Composable
fun HistoryPreview() {
    /**
    TracktelandroidTheme {
        val trackday = TrackDay(
            id = 0,
            name = "Silverstone Circuit",
            dateTime = LocalDate.now(),
            lapCount = 4,
            bestLap = 126L,
            averageLap = 129L,
            vMax = 114.9F,
            laps = listOf(126L, 131L, 134L, 129L)
        )
        val trackday1 = TrackDay(
            id = 0,
            name = "Donington Park",
            dateTime = LocalDate.now(),
            lapCount = 4,
            bestLap = 126L,
            averageLap = 126L,
            vMax = 114.9F,
            laps = listOf(126L, 131L, 134L, 129L)
        )
        val trackday2 = TrackDay(
            id = 0,
            name = "Knock Hill",
            dateTime = LocalDate.now(),
            lapCount = 4,
            bestLap = 126L,
            averageLap = 129L,
            vMax = 114.9F,
            laps = listOf(126L, 131L, 134L, 129L)
        )
        var trackdays = mutableListOf<TrackDay>()

        trackdays.add(trackday)
        trackdays.add(trackday1)
        trackdays.add(trackday2)

        var navController: NavController
        //HistoryScreen(trackdays, navController = navController)
    }
    */
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HistoryScreen(
    trackDayFactory: TrackDayViewModelFactory,
    navController: NavController,
    trackDayViewModel: TrackDayViewModel = viewModel(factory = trackDayFactory)){

    val trackDayList = trackDayViewModel.trackDayList.collectAsState(initial = emptyList())


    Scaffold(
        topBar = {
            TrackTopAppBar(navController, "Trackday History")
        },
        bottomBar = { TrackBottomAppBar(navController) }

    ){
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()){
            items(trackDayList.value){ trackday ->
                TrackDayCard(trackday = trackday)
            }
        }
    }
}