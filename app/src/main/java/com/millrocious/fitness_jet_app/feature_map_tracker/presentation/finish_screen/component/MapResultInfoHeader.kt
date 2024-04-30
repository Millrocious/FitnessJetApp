package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.outlined.DoNotStep
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.core.presentation.util.MetricsUtil
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.RunState

@Composable
fun MapResultInfoHeader(
    runState: RunState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(20.dp)
                )
        ) {
            MapResultItem(
                value = MetricsUtil.formatTime(runState.durationInMillis),
                icon = Icons.Default.AccessAlarm,
                title = "Time elapsed",
                metric = ""
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(20.dp)
                )
        ) {
            MapResultItem(
                value = runState.steps.toString(),
                icon = Icons.Outlined.DoNotStep,
                title = "Steps",
                metric = "steps"
            )
        }
    }
}