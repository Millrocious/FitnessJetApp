package com.millrocious.fitness_jet_app.core.presentation.util

sealed class Screen(val route: String) {
    object HeartRatesScreen: Screen("heart_rates_screen")
    object HeartRatesListScreen: Screen("heart_rates_list_screen")
    object AddEditHeartRateScreen: Screen("add_edit_heart_rate_screen")

    object BloodPressuresScreen: Screen("blood_pressures_screen")
    object BloodPressuresListScreen: Screen("blood_pressures_list_screen")
    object AddEditBloodPressureScreen: Screen("add_edit_blood_pressure_screen")

    object MetricsScreen: Screen("metrics_screen")
    object MapScreen: Screen("map_screen")
}