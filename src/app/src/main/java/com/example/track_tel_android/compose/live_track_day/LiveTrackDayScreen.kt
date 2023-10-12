package com.example.track_tel_android.compose.live_track_day

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.track_tel_android.compose.track.TrackCard
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.ui.theme.TrackTopAppBar
import com.example.track_tel_android.ui.theme.TracktelandroidTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.track_tel_android.*
import com.example.track_tel_android.data.trackday.TrackDay
import org.intellij.lang.annotations.JdkConstants
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TracktelandroidTheme {
        //LiveTrackDayScreen()
    }
}

private const val timingFontSize = 40
private val timingFontcolor = androidx.compose.ui.graphics.Color.Gray

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LiveTrackDayScreen(
    navController: NavController,
    addTrackDayViewModelFactory: AddTrackDayViewModelFactory,
    viewModel: LiveTrackDayViewModel = viewModel()){

    val addTrackDayViewModel: AddTrackDayViewModel = viewModel(factory = addTrackDayViewModelFactory)


    Scaffold(
        topBar = {
            TrackTopAppBar(
                navController = navController,
                title = "Live TrackDay")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            //viewModel.startSession()
            LiveTiming(
                viewModel.averageLap,
                viewModel.bestLap)
            CurrentSpeed(currentSpeed = 86, units = "MPH")
            LapsCompleted(lapsCompleted = viewModel.lapCount)
            EndSessionButton(navController = navController,
                endSession = { viewModel.endSession() },
                startSession = { viewModel.startSession() },
                sessionInProgress = viewModel.sessionInProgress,
            viewModel,
            addTrackDayViewModel)
            NewLapButton(newLap = {viewModel.newLap()})
        }
    }

}

@Composable
fun NewLapButton(
    newLap: () -> Unit){

    Button(onClick = {
        newLap() },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()) {
        Text(text = "New Lap Debug")
    }
}



@Composable
fun EndSessionButton(
    navController: NavController,
    endSession: () -> Unit,
    startSession: () -> Unit,
    sessionInProgress: Boolean,
    viewModel: LiveTrackDayViewModel,
    addTrackDayViewModel: AddTrackDayViewModel){

    if (!sessionInProgress){
        Button(
            onClick = {
                startSession()
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Text(text = "START SESSION", fontSize = 24.sp)
        }
    }else{
        Button(
            onClick = {
                endSession()
                saveTrackDay(viewModel, addTrackDayViewModel)
                navController.navigate("track_list")
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Text(text = "END SESSION", fontSize = 24.sp)
        }
    }

}

private fun saveTrackDay(
    viewModel: LiveTrackDayViewModel,
    addTrackDayViewModel: AddTrackDayViewModel){

    val trackDayToSave = TrackDay(
        0,
        "test_save",
        LocalDateTime.now(),
        viewModel.lapCount,
        viewModel.bestLap,
        viewModel.averageLap,
        vMax = 0.0F,
        viewModel.laps
    )

    addTrackDayViewModel.addTrackDay(trackDayToSave)


}

@Composable
fun LiveTiming(averageLapTime: Long, bestLapTime: Long){
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier
        .fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "Current: ",
                fontSize = timingFontSize.sp,
                color = timingFontcolor)
            Text(
                text = "Best: ",
                fontSize = timingFontSize.sp,
                color = timingFontcolor)
            Text(
                text = "Average: ",
                fontSize = timingFontSize.sp,
                color = timingFontcolor)
        }
        Column(horizontalAlignment = Alignment.Start) {
            CurrentLapTime()
            BestLapTime(bestLapTime = bestLapTime)
            AverageLapTime(averageLapTime = averageLapTime)
        }
    }

}

@Composable
fun CurrentLapTime(){
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = formatLapTime(currentLapTime),
            fontSize = timingFontSize.sp,
            color = timingFontcolor)
    }
}
@Composable
fun BestLapTime(bestLapTime: Long){
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = formatLapTime(bestLapTime),
            fontSize = timingFontSize.sp,
            color = timingFontcolor)
    }
}
@Composable
fun AverageLapTime(averageLapTime: Long){
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = formatLapTime(averageLapTime),
            fontSize = timingFontSize.sp,
            color = timingFontcolor)
    }
}

@Composable
fun CurrentSpeed(currentSpeed: Int, units: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = currentSpeed.toString(),
            fontSize = 48.sp,
            color = timingFontcolor)
        Text(
            text = units,
            fontSize = 44.sp,
            color = timingFontcolor)
    }
}

@Composable
fun LapsCompleted(lapsCompleted: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = (lapsCompleted - 1).toString(),
            fontSize = 48.sp,
            color = timingFontcolor)
        Text(
            text = "Laps Completed",
            fontSize = 44.sp,
            color = timingFontcolor)
    }
}
