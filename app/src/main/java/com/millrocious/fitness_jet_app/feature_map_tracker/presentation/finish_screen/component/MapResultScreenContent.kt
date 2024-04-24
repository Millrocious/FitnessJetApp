package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.RunState

@Composable
fun MapResultScreenContent(
    runState: RunState
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                bitmap = runState.img.asImageBitmap(),
                contentDescription = "Run Map",
                modifier = Modifier.aspectRatio(1f)
            )
            MapResultInfo(runState = runState)
        }
    }
}