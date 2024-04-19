package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.bottom_card

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service.LocationService
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.MapEvent

@Composable
fun MapBottomMenu(
    modifier: Modifier = Modifier,
    state: CurrentRunState,
    onEvent: (MapEvent) -> Unit,
    onFinish: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MapBottomMenuHeader(state)
            MapBottomMenuMetrics(state)
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.errorContainer,
                            RoundedCornerShape(15.dp)
                        ),
                    onClick = { onFinish() }
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Default.Done,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(15.dp)
                        ),
                    onClick = { onEvent(MapEvent.ResumePauseTracking) }
                ) {
                    Log.d("SERVICE_STATE", LocationService.SERVICE_STATE.toString())
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = if (!state.isTracking) Icons.Default.PlayArrow else Icons.Default.Refresh,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MapBottomPrev() {
    MapBottomMenu(
        state = CurrentRunState(
            0, 0f, false, 0L, 0L
        ),
        onFinish = {},
        onEvent = {}
    )
}