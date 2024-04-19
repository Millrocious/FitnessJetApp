package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.bottom_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.util.MetricsUtil

@Composable
fun MapBottomMenuMetrics(state: CurrentRunState) {
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricItem(
                icon = Icons.Default.LocationOn,
                title = "Distance",
                value = MetricsUtil.formatDistance(state.distanceInMeters)
            )
            MetricItem(
                icon = Icons.Default.Person,
                title = "Steps",
                value = state.steps.toString()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricItem(
                icon = Icons.Default.Star,
                title = "Speed",
                value = MetricsUtil.formatSpeed(state.speedInKMH)
            )
            MetricItem(
                icon = Icons.Default.Favorite,
                title = "Calories",
                value = "0"
            )
        }
    }
}

@Preview
@Composable
private fun MapBottomMenuMetricsPrev() {
    MapBottomMenuMetrics(
        state = CurrentRunState(
            0, 0f, false, 0L, 0L
        )
    )
}
