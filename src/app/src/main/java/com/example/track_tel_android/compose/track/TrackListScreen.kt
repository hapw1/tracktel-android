package com.example.track_tel_android.compose.track

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.track_tel_android.TrackViewModel
import com.example.track_tel_android.TrackViewModelFactory
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.track_tel_android.TrackDayViewModelFactory
import com.example.track_tel_android.data.Track
import com.example.track_tel_android.ui.theme.TrackBottomAppBar
import com.example.track_tel_android.ui.theme.TrackTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrackListScreen(
    factory: TrackViewModelFactory,
    modifier: Modifier = Modifier,
    trackViewModel: TrackViewModel = viewModel(factory = factory),
    navController: NavController
){

    val trackList = trackViewModel.trackList.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TrackTopAppBar(navController, "Track Select")
        },
        bottomBar = { TrackBottomAppBar(navController) }
    ){
        Column(modifier = modifier.padding(8.dp, 8.dp, 8.dp, 64.dp)) {

            LazyVerticalGrid(columns = GridCells.Fixed(2)){
                items(trackList.value) { item ->
                    TrackCard(track = item, navController = navController)
                }
            }

        }
    }


}

private fun deleteTrack(deleteTrack: (Track) -> Unit){
    val track = Track(
        0,
        "Silverstone Circuit",
        "Banbury, Oxfordshire",
        3.67F,
        18,
        85.0,
        52.06932,
        1.02246,
        52.06910,
        1.02202,
        52.06922,
        1.02226,
        true,
        167845L,
        "image_path"
    )
    deleteTrack(track)

}

private fun addTrack(addAllTracks : (List<Track>) -> Unit){

    val track = Track(
        0,
        "Donington Park",
        "Nottingham, Leicestershire",
        2.5F,
        9,
        1.0,
        52.82967,
        1.37960,
        52.83001,
        1.37949,
        52.82984,
        1.37954,
        true,
        167845L,
        "donington_map")

    val tracks: MutableList<Track> = arrayListOf()

    tracks.add(Track(
        0,
        "Silverstone Circuit",
        "Banbury, Oxfordshire",
        3.67F,
        18,
        85.0,
        52.06932,
        1.02246,
        52.06910,
        1.02202,
        52.06922,
        1.02226,
        true,
        167845L,
        "silverstone_map"
    ))

    tracks.add(Track(
        0,
        "Prescott Hill Climb",
        "Cheltenham, Gloucesteshire",
        0.64F,
        8,
        1.0,
        51.96309,
        2.02383,
        51.96315,
        2.02415,
        51.96311,
        2.02400,
        false,
        167845L,
        "prescott_map"
    ))

    tracks.add(Track(
        0,
        "Donington Park",
        "Nottingham, Leicestershire",
        2.5F,
        9,
        1.0,
        52.82967,
        1.37960,
        52.83001,
        1.37949,
        52.82984,
        1.37954,
        true,
        167845L,
        "donington_map"))


    addAllTracks(tracks)
}

