package com.millrocious.fitness_jet_app.feature_map_tracker.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import styleJson


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    // Set properties using MapProperties which you can use to recompose the map
    val mapProperties = MapProperties(
        // Only enable if user has accepted location permissions.
        isMyLocationEnabled = true,
        mapStyleOptions = MapStyleOptions(styleJson),

        )

    var waiter by remember {
        mutableStateOf(false)
    }

    val cameraPositionState = rememberCameraPositionState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            //if (waiter) {
                GoogleMap(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp)),
                    //.clip(RoundedCornerShape(30.dp))
                    //.padding(start = 20.dp, top = 60.dp, end = 20.dp),
                    properties = mapProperties,
                    cameraPositionState = cameraPositionState
                )
            //}
        }

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //modifier = Modifier.padding(40.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {}) {
                        Text(text = "Action button")
                    }
                    Button(onClick = {
                        GlobalScope.launch(Dispatchers.Main) {
                            navController.navigate(Screen.HeartRatesScreen.route)
                        }
                    }) {
                        Text(text = "Action button")
                    }
                }

            }
        }
    }
}

//@Composable
//fun MapBox() {
//    val mapProperties = MapProperties(
//        // Only enable if user has accepted location permissions.
//        isMyLocationEnabled = true,
//        mapStyleOptions = MapStyleOptions(styleJson),
//
//        )
//
//    val cameraPositionState = rememberCameraPositionState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        GoogleMap(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.75f)
//                .clip(RoundedCornerShape(30.dp))
//                .padding(start = 20.dp, top = 60.dp, end = 20.dp),
//            properties = mapProperties,
//            cameraPositionState = cameraPositionState
//        ) {
//        }
//        Box(
//            modifier = Modifier
//                .padding(20.dp)
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(20.dp),
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                //modifier = Modifier.padding(40.dp)
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Button(onClick = {}) {
//                        Text(text = "Action button 1")
//                    }
//                    Button(onClick = {}) {
//                        Text(text = "Action button 2")
//                    }
//                }
//
//            }
//        }
//    }
//}


//@Preview
//@Composable
//fun MapBoxPrev() {
//    MapBox()
//}
