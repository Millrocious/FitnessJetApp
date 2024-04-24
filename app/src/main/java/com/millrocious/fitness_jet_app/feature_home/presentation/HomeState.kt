package com.millrocious.fitness_jet_app.feature_home.presentation

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run

data class HomeState(
    val totalSteps: Long? = null,
    val runList: List<Run> = emptyList(),
)