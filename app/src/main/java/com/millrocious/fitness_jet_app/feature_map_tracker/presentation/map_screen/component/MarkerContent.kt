package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MarkerContent(distanceToMarker: Int) {
    val markerState = rememberMarkerState(position = LatLng(49.126308, 24.721064))

    MarkerComposable(
        keys = arrayOf(distanceToMarker),
        state = markerState
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(2.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "$distanceToMarker m",
                    fontSize = 8.sp,
                    color = Color.White
                )
            }
        }
    }
}