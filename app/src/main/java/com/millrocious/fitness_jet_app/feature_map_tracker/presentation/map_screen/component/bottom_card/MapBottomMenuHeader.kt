package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.bottom_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.util.MetricsUtil

@Composable
fun MapBottomMenuHeader(
    state: CurrentRunState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Time", fontSize = 16.sp)
            Text(
                text = MetricsUtil.formatTime(state.timeDuration),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun MapBottomMenuHeaderPrev() {
    MapBottomMenuHeader(
        state = CurrentRunState(
            0, 0f, false, 0L, 0L
        )
    )
}