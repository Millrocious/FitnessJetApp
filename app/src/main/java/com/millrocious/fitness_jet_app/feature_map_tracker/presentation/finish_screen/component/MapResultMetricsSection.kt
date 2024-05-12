package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.RunState

@Composable
fun MapResultMetricsSection(
    runState: RunState
) {
    Text(
        modifier = Modifier.padding(top = 10.dp, start = 15.dp),
        text = "Metrics",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(20.dp)
            )
    ) {
        MapResultItem(
            value = runState.distanceInMeters.toString(),
            icon = Icons.Default.LocationOn,
            title = "Distance",
            metric = "km"
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.background
        )
        MapResultItem(
            value = runState.caloriesBurned.toString(),
            icon = Icons.Default.LocalFireDepartment,
            title = "Calories",
            metric = "kcal"
        )
    }
}