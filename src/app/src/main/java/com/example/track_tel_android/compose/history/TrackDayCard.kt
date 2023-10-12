package com.example.track_tel_android.compose.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.formatDateTime
import com.example.track_tel_android.formatLapTime
import com.example.track_tel_android.ui.theme.Purple500
import com.example.track_tel_android.ui.theme.TracktelandroidTheme
import java.time.LocalDate


@Preview(showBackground = true)
@Composable
fun TrackDayCardPreview() {
    TracktelandroidTheme {
        /**
        val trackday = TrackDay(
            id = 0,
            name = "Silverstone Circuit",
            dateTime = LocalDate.now(),
            lapCount = 4,
            bestLap = 126L,
            averageLap = 129L,
            vMax = 114.9F,
            laps = listOf(126000L, 131000L, 134000L, 129000L)
        )
        TrackDayCard(trackday = trackday)
        */
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackDayCard(trackday: TrackDay){

    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 4.dp),
        onClick = {
            expanded = !expanded
        }
    ) {
        TrackDayCardData(trackday, expanded)
    }
}

@Composable
fun TrackDayCardData(trackDay: TrackDay, expanded: Boolean){
    var height = 0
    trackDay.laps.forEach{height += 57}

    Column (verticalArrangement = Arrangement.SpaceEvenly
        ,modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)){
        Row() {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = trackDay.name,
                    color = Purple500,
                fontSize = 24.sp)
                //Spacer(modifier = Modifier.height(5.dp))
                Text(text = formatDateTime(trackDay.dateTime),
                    color = Color.Gray,
                    fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Laps: ${trackDay.lapCount}",
                    color = Color.Gray,
                fontSize = 14.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Best Lap: ${formatLapTime(trackDay.bestLap)}",
                    color = Color.Gray,
                    fontSize = 14.sp)
            }
            //Spacer(modifier = Modifier.width(40.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Average Lap: ${formatLapTime(trackDay.averageLap)}",
                    color = Color.Gray,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "VMax: ${trackDay.vMax}",
                    color = Color.Gray,
                    fontSize = 14.sp)
            }
        }
        if (expanded){
            Spacer(modifier = Modifier.height(15.dp))
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .height(200.dp)
                ){
                    items(trackDay.laps){ lap ->
                        LapCard(lap = lap, averageLap = trackDay.averageLap)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DeleteTrackDayDataButton()
                }
            }


        }
    }
}

@Composable
fun DeleteTrackDayDataButton(){
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFd12932)))
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
            Text(text = "DELETE TRACKDAY DATA")
            Icon(Icons.Default.Delete, "Delete")
        }

    }


}

