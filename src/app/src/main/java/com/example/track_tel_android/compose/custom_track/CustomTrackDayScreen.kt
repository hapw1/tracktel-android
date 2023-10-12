package com.example.track_tel_android.compose.custom_track

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.track_tel_android.ui.theme.TracktelandroidTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var circuitState = mutableStateOf(true)
private var mphState = mutableStateOf(true)


@Preview(showBackground = true)
@Composable
fun CustomTrackPreview(){
    TracktelandroidTheme {
        CustomTrackScreen()
    }
}

@Composable
fun CustomTrackScreen(){
    TrackSetup()
}

@Composable
fun TrackSetup(){

    var trackState by remember { mutableStateOf("")}

    val onTextChange = {text : String ->
        trackState = text
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TrackDayName(trackState = trackState)



        CircuitOrSprint()

        MphOrKph()
        
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            Button(onClick = { /*TODO*/ }) {
                if(circuitState.value){
                    Text(text = "Choose Start Line")
                }else{
                    Text(text = "Choose Start and Finish Line")

                }
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            Text(text = "Start Line Position")
            Text(text = "Latitude: lat")
            Text(text = "Longitude: long")
        }
        if (!circuitState.value){
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                Text(text = "Finish Line Position")
                Text(text = "Latitude: lat")
                Text(text = "Longitude: long")
            }
        }





    }

}

@Composable
fun TrackDayName(trackState: String){
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)){
        OutlinedTextField(
            value = trackState,
            onValueChange = {},
            singleLine = true,
            label = { Text(text = "Track Name")}
        )

    }
}

@Composable
fun CircuitOrSprint(){
    val trackTypes = listOf("Circuit", "Sprint")
    
    val (selectedOption, onOptionsSelected) = remember { mutableStateOf(trackTypes[0])}
    
    Row {
        trackTypes.forEach { text ->
            Column(modifier = Modifier
                .wrapContentHeight()
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionsSelected(text)
                    }
                )
                .padding(8.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption), 
                    onClick = { onOptionsSelected(text) })
                Text(text = text)
            }
        }
    }
}

@Composable
fun MphOrKph(){
    val unitType = listOf("MPH", "KPH")

    val (selectedOption, onOptionsSelected) = remember { mutableStateOf(unitType[0])}

    Row {
        unitType.forEach { text ->
            Column(modifier = Modifier
                .wrapContentHeight()
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionsSelected(text)
                    }
                )
                .padding(8.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionsSelected(text) })
                Text(text = text)
            }
        }
    }
}