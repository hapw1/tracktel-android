package com.example.track_tel_android.compose.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.track_tel_android.formatLapTime
import kotlin.math.round


@Composable
fun LapCard(lap: Long, averageLap: Long){
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .padding(4.dp, 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = 8.dp
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            if (lap > averageLap){
                Text(text = formatLapTime(lap), fontSize = 24.sp, color = Color(0xFFd12932))
                Icon(Icons.Default.ArrowCircleUp,
                    "Slower",
                    tint = Color(0xFFd12932))

            }else{
                Text(text = formatLapTime(lap), fontSize = 24.sp, color = Color(0xFF049931))
                Icon(Icons.Default.ArrowCircleDown,
                    "Faster",
                    tint = Color(0xFF049931))

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LapCardPreview(){
    LapCard(lap = 167L, averageLap = 170L)
}

