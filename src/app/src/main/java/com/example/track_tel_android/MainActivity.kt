package com.example.track_tel_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.track_tel_android.compose.history.HistoryScreen
import com.example.track_tel_android.compose.live_track_day.LiveTrackDayScreen
import com.example.track_tel_android.compose.track.TrackListScreen
import com.example.track_tel_android.data.*
import com.example.track_tel_android.data.trackday.TrackDay
import com.example.track_tel_android.data.trackday.TrackDayDatabase
import com.example.track_tel_android.data.trackday.TrackDayRepositoryImpl
import com.example.track_tel_android.ui.theme.TracktelandroidTheme
import java.time.LocalDate
val mainColor = Color(60, 172, 174)
val accentColor = Color(200, 244, 249)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dao = TrackDatabase.getInstance(application).trackDao
        val repository = TrackRepositoryImpl(dao)
        val factory = TrackViewModelFactory(repository)

        val trackDayDao = TrackDayDatabase.getInstance(application).trackDayDao
        val trackDayRepository = TrackDayRepositoryImpl(trackDayDao)
        val trackDayFactory = TrackDayViewModelFactory(trackDayRepository)

        val addTrackDayFactory = AddTrackDayViewModelFactory(trackDayRepository)

        setContent {
            TracktelandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = mainColor
                ) {
                    //TrackListScreen(factory = factory)
                    TrackTelApp(factory = factory, trackDayFactory, addTrackDayFactory)
                }
            }
        }
    }
}

@Composable
fun TrackTelApp(
    factory: TrackViewModelFactory,
    trackDayFactory: TrackDayViewModelFactory,
    addTrackDayFactory: AddTrackDayViewModelFactory){
    val navController = rememberNavController()

    NavHost(navController, startDestination = "track_list"){
        composable(route = "track_list"){
            TrackListScreen(factory = factory, navController = navController)
        }
        composable(route = "live_track_day"){
            LiveTrackDayScreen(navController = navController, addTrackDayFactory)
        }
        composable(route = "history"){
            HistoryScreen(trackDayFactory = trackDayFactory, navController)
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TracktelandroidTheme {
        Greeting("Android")
    }
}