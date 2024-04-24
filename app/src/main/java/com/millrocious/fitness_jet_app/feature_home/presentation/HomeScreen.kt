package com.millrocious.fitness_jet_app.feature_home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.feature_home.presentation.component.RunList


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state

    //TotalStepsCard(totalSteps = state.totalSteps)
    RunList(state = state)
}