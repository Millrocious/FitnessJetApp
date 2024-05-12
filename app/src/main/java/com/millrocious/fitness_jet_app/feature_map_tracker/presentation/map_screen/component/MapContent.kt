package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Polyline
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunStateWithCalories
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.LocationPoint
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils.lasLocationPoint
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.MapEvent
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.bottom_card.MapBottomMenu
import kotlin.math.roundToInt

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapContent(
    navController: NavController,
    mapProperties: MapProperties,
    state: CurrentRunStateWithCalories,
    cameraPositionState: CameraPositionState,
    onEvent: (MapEvent) -> Unit,
    onSnapshot: (Bitmap) -> Unit,
) {
    var mapSize by remember { mutableStateOf(Size(0f, 0f)) }
    var mapCenter by remember { mutableStateOf(Offset(0f, 0f)) }
    var isRunningFinished by remember { mutableStateOf(false) }
    var distanceToMarker by remember { mutableIntStateOf(0) }

    var latLngList by remember { mutableStateOf(mutableListOf<LocationPoint>()) }
    var previousSpeed by remember { mutableStateOf(UserSpeed.SLOW) }

    var polylineList by remember { mutableStateOf(listOf<List<LocationPoint>>()) }

    LaunchedEffect(key1 = state.currentRunState.pathPoints.lasLocationPoint()) {
        state.currentRunState.pathPoints.lasLocationPoint()?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(it.latLng, 15f)
                )
            )

            val result = FloatArray(1)
            Location.distanceBetween(
                it.latLng.latitude,
                it.latLng.longitude,
                LatLng(49.126308, 24.721064).latitude,
                LatLng(49.126308, 24.721064).longitude,
                result
            )

            if (latLngList.isNotEmpty()) {
                val lastSpeed = latLngList.last().speed
                if (lastSpeed != previousSpeed) {

                    val previousLocationPoint = latLngList.last().copy(speed = lastSpeed)
                    latLngList.clear()
                    latLngList.add(previousLocationPoint)
                    previousSpeed = lastSpeed
                }
            }

            latLngList.add(LocationPoint(it.latLng, it.speed))
            polylineList = polylineList + listOf(latLngList.toList())

            distanceToMarker = result[0].roundToInt()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .height(480.dp)
                .drawBehind {
                    mapSize = size
                    mapCenter = center
                },
            properties = mapProperties,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
            ),
            cameraPositionState = cameraPositionState
        ) {
            MapEffect(key1 = isRunningFinished) { map ->
                if (isRunningFinished) {
                    RunUtils.takeSnapshot(
                        map,
                        state.currentRunState.pathPoints,
                        mapCenter,
                        onSnapshot,
                        snapshotSideLength = mapSize.width
                    )
                }
            }

            polylineList.forEach { polylinePoints ->
                Polyline(
                    startCap = RoundCap(),
                    endCap = RoundCap(),
                    points = polylinePoints.map { it.latLng }.toList(),
                    jointType = JointType.ROUND,
                    width = 15F,
                    color = getColorBySpeed(polylinePoints.first().speed)
                )
            }

            MarkerContent(distanceToMarker = distanceToMarker)
        }
        MapBottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter),
            state = state,
            onEvent = { event ->
                onEvent(event)
            },
            onFinish = {
                isRunningFinished = true
            }
        )
    }
}

// Helper function to get color based on speed category
private fun getColorBySpeed(speed: UserSpeed): Color {
    return when (speed) {
        UserSpeed.SLOW -> Color(0xFFC4E066)
        UserSpeed.MEDIUM -> Color(0xFFE0CC6D)
        UserSpeed.FAST -> Color(0xFFE08266)
    }
}

enum class UserSpeed {
    SLOW,
    MEDIUM,
    FAST
}
