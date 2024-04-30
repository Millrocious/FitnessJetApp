package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.bottom_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.DoNotStep
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.core.presentation.util.MetricsUtil
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState

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
                icon = Icons.Outlined.DoNotStep,
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
                icon = Icons.Default.ElectricBolt,
                title = "Speed",
                value = MetricsUtil.formatSpeed(state.speedInKMH)
            )
            MetricItem(
                icon = Icons.Default.LocalFireDepartment,
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

