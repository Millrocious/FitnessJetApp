package com.millrocious.fitness_jet_app.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        RunList(state = state)
    }
}