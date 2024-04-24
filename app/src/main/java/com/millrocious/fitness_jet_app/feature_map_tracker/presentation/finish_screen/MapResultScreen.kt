package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component.MapResultScreenContent


@Composable
fun MapResultScreen(
    navController: NavController,
    viewModel: MapResultViewModel = hiltViewModel()
) {
    val runState by viewModel.state

    MapResultScreenContent(runState = runState)
}


