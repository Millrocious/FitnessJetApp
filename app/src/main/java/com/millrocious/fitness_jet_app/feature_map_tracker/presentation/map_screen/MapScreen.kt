package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.MapContent
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.styleJson


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val currentRunState by viewModel.currentRunState.collectAsStateWithLifecycle()

    var isMapLoaded by remember { mutableStateOf(false) }
    var isMapLoadedFirst by remember { mutableStateOf(0) }

    val context = LocalContext.current

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    val mapProperties = MapProperties(
        isMyLocationEnabled = true,
        mapStyleOptions = if (isSystemInDarkTheme()) MapStyleOptions(styleJson) else null,

    )

    val cameraPositionState = rememberCameraPositionState()

    if (locationPermissionsState.allPermissionsGranted) {
        MapContent(
            navController = navController,
            mapProperties = mapProperties,
            state = currentRunState,
            cameraPositionState = cameraPositionState,
            onEvent = { event ->
                viewModel.onEvent(event)
            },
            onSnapshot = { bitmap ->
                viewModel.finishRun { currentRunId ->
                    currentRunId?.let {
                        Log.d("CurrentRunId", it)

                        navController.navigate(
                            Screen.FinishMapScreen.route +
                                    "?runId=${currentRunId}")
                    }
                }

                viewModel.onEvent(MapEvent.FinishRun(bitmap))
            },
        )
    } else {
        Surface(modifier = Modifier.fillMaxSize()) {
            LaunchedEffect(Unit) {
                locationPermissionsState.launchMultiplePermissionRequest()
            }
        }
    }
}