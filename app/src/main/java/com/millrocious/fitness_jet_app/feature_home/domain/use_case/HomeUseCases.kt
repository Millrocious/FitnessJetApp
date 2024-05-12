package com.millrocious.fitness_jet_app.feature_home.domain.use_case

data class HomeUseCases(
    val getAllRun: GetAllRun,
    val getAllStepsByToday: GetAllStepsByToday,
    val getAllBurnedCaloriesByToday: GetAllBurnedCaloriesByToday
)