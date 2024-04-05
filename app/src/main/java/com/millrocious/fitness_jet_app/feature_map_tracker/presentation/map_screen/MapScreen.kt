package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.styleJson


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val state by viewModel.state

    val context = LocalContext.current

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    val mapProperties = MapProperties(
        isMyLocationEnabled = true,
        mapStyleOptions = MapStyleOptions(styleJson),
    )

    val cameraPositionState = rememberCameraPositionState()

    if (locationPermissionsState.allPermissionsGranted) {
        MapContent(
            navController = navController,
            mapProperties = mapProperties,
            state = state,
            cameraPositionState = cameraPositionState,
            onEvent = { event ->
                viewModel.onEvent(event)
            }
        )
    }
    else {
        Surface(modifier = Modifier.fillMaxSize()) {
            LaunchedEffect(Unit) {
                locationPermissionsState.launchMultiplePermissionRequest()
            }
        }
    }
}

@Composable
fun MapContent(
    navController: NavController,
    mapProperties: MapProperties,
    state: MapState,
    cameraPositionState: CameraPositionState,
    onEvent: (MapEvent) -> Unit
) {
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
            GoogleMap(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp)),
                properties = mapProperties,
                cameraPositionState = cameraPositionState
            ) {
//                if (state.lastKnownLocation != null) {
//                    Marker(
//                        state = MarkerState(position = LatLng(state.lastKnownLocation.latitude, state.lastKnownLocation.longitude)),
//                        title = "My Location",
//                        snippet = "This is my current location"
//                    )
//                }

                if (state.pathPoints.isNotEmpty()) {
                    Polyline(
                        points = state.pathPoints,
                        jointType = JointType.ROUND,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        width = 15F,
                        zIndex = 1f,
                    )
                    Polyline(
                        points = state.pathPoints,
                        jointType = JointType.ROUND,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        width = 30F
                    )
                }
            }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { onEvent(MapEvent.StartLocationService) }) {
                        Text(text = "Start service")
                    }
                    Button(onClick = { onEvent(MapEvent.StopLocationService) }) {
                        Text(text = "Stop service")
                    }
                }
            }
        }
    }
}