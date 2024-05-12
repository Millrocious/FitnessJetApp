package com.millrocious.fitness_jet_app.feature_map_tracker.domain.model

data class CurrentRunStateWithCalories(
    val currentRunState: CurrentRunState = CurrentRunState(),
    val caloriesBurnt: Int = 0,
)