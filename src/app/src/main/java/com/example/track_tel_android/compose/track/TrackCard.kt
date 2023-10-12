package com.example.track_tel_android.compose.track


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.track_tel_android.R
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.formatLapTime
import com.example.track_tel_android.ui.theme.Purple500
import com.example.track_tel_android.ui.theme.TracktelandroidTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackCard(track: Track, navController: NavController){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 4.dp),
        onClick = {navController.navigate("live_track_day")}
    ) {

        Column{
            TrackCardTopRow(track = track)
            TrackImage(imagePath = track.trackImagePath)
            TrackCardBottomRow(track = track)
        }

    }
}

@Composable
fun TrackCardTopRow(track: Track){
    Row{
        DisplayIcon(isCircuit = track.isCircuit)
        Column(modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)) {
            TrackNameRow(trackName = track.name)
            TrackCountyRow(trackCounty = track.county)
        }
    }
}

@Composable 
fun TrackCardBottomRow(track: Track){
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly) {

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            TrackLengthRow(trackLength = track.length)
            TrackTurnsRow(trackTurns = track.turns)
        }
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            TrackBestLapRow(bestLap = track.bestLap)
            TrackDistanceRow(distanceFromUser = track.distanceFromUser)
        }
    }
}

@Composable
fun DisplayIcon(isCircuit: Boolean){
    
    Column(modifier = Modifier
        .padding(9.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if(isCircuit){
            Image(painterResource(R.drawable.circuit_icon),
                contentDescription = "Circuit Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(35.dp)
            )
        }else{
            Image(painterResource(R.drawable.sprint_icon),
                contentDescription = "Sprint Icon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(35.dp)
            )
        }
        
    }
}


@Composable
fun TrackNameRow(trackName: String){
    Row {
       Text(
           text = trackName,
           color = Purple500,
           fontSize = 14.sp,
           fontWeight = FontWeight.Light
       )
    }
}

@Composable
fun TrackCountyRow(trackCounty: String){
    Row{
        Text(
            text = trackCounty,
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun TrackLengthRow(trackLength: Float){
    Row {
        Text(
            text = "Length:        $trackLength",
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
fun TrackTurnsRow(trackTurns: Int){
    Row {
        Text(
            text = "Turns:          $trackTurns",
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
fun TrackBestLapRow(bestLap: Long){
    Row {
        Text(
            text = "Best Lap:     ${formatLapTime(bestLap)}",
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
fun TrackDistanceRow(distanceFromUser: Double){
    Row {
        Text(
            text = "$distanceFromUser miles away",
            color = Color.DarkGray,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

@Composable
fun TrackImage(imagePath: String){
    val context = LocalContext.current
    val drawableID = remember(imagePath){
        context.resources.getIdentifier(
            imagePath,
            "drawable",
            context.packageName
        )
    }
    Column {
        Image(painterResource(id = drawableID),
            contentDescription = "Map of Silverstone Circuit",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(
                        1.dp,
                        color = Color.LightGray
                    )
                )
                .size(130.dp)
        )
    }

}