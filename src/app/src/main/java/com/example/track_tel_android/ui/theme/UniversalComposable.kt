package com.example.track_tel_android.ui.theme

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun TrackTopAppBar(navController: NavController, title: String){
    TopAppBar(
        title =
        {
            Text(text = title)
        },
        actions =
        {}
    )
}

@Composable
fun TrackBottomAppBar(navController: NavController){
    val selectedIndex = remember { mutableStateOf(0)}

    BottomNavigation(elevation = 10.dp
    ) {

        BottomNavigationItem(
            icon = {
                   Icon(Icons.Default.List, "Tracks")
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                navController.navigate("track_list")
                selectedIndex.value = 0
            })

        BottomNavigationItem(
            icon = {
                Icon(Icons.Default.History, "History")
            },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate("history")
            })

    }
}