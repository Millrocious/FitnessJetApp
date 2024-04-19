package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Polyline
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.PathPoint
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
    state: CurrentRunState,
    cameraPositionState: CameraPositionState,
    onEvent: (MapEvent) -> Unit,
    onSnapshot: (Bitmap) -> Unit,
) {
    var mapSize by remember { mutableStateOf(Size(0f, 0f)) }
    var mapCenter by remember { mutableStateOf(Offset(0f, 0f)) }
    var isRunningFinished by remember { mutableStateOf(false) }
    var distanceToMarker by remember { mutableIntStateOf(0) }
    val latLngList = mutableListOf<LatLng>()

    LaunchedEffect(key1 = state.pathPoints.lasLocationPoint()) {
        state.pathPoints.lasLocationPoint()?.let {
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
                        state.pathPoints,
                        mapCenter,
                        onSnapshot,
                        snapshotSideLength = mapSize.width / 2
                    )
                }
            }

            state.pathPoints.forEach { pathPoint ->
                if (pathPoint is PathPoint.LocationPoint) {
                    latLngList += pathPoint.latLng
                    Polyline(
                        points = latLngList.toList(),
                        jointType = JointType.ROUND,
                        width = 15F,
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                }
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
