package com.millrocious.fitness_jet_app.feature_home.presentation

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run

data class HomeState(
    val totalSteps: Long? = 0,
    val totalBurnedCalories: Int? = 0,
    val runList: List<Run> = emptyList(),
    val stepsGoal: Int = 5000,
    val burnedCaloriesGoal: Int = 300,
)