package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.RunState

@Composable
fun MapResultInfo(
    runState: RunState
) {
    Column(
        modifier = Modifier
            .padding(top = 340.dp)
            .fillMaxHeight()
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
    ) {
        MapResultInfoHeader(runState = runState)
        MapResultMetricsSection(runState = runState)
        ActionsSection()
    }
}

@Preview(device = "id:pixel_6")
@Composable
private fun MapResultScreenPrev() {
    MapResultInfo(runState = RunState())
}