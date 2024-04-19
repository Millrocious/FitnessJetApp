package com.millrocious.fitness_jet_app.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.millrocious.fitness_jet_app.core.presentation.screen.metrics.MetricsScreen
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.MapScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure.AddEditBloodPressureScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures.BloodPressuresScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list.BloodPressuresListScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate.AddEditHeartRateScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates.HeartRatesScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list.HeartRatesListScreen


@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MetricsScreen.route
    ) {
        composable(
            route = Screen.MapScreen.route,
        ) {
            MapScreen(navController = navController)
        }
        // Heart rate feature
        composable(
            route = Screen.HeartRatesScreen.route,
        ) {
            HeartRatesScreen(navController = navController)
        }
        composable(
            route = Screen.HeartRatesListScreen.route,
        ) {
            HeartRatesListScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditHeartRateScreen.route +
                    "?heartRateId={heartRateId}",
            arguments = listOf(
                navArgument(
                    name = "heartRateId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddEditHeartRateScreen(navController = navController)
        }
        // Blood pressure feature
        composable(
            route = Screen.BloodPressuresScreen.route,
        ) {
            BloodPressuresScreen(navController = navController)
        }
        composable(
            route = Screen.BloodPressuresListScreen.route,
        ) {
            BloodPressuresListScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditBloodPressureScreen.route +
                    "?bloodPressureId={bloodPressureId}",
            arguments = listOf(
                navArgument(
                    name = "bloodPressureId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddEditBloodPressureScreen(navController = navController)
        }
        //
        composable(
            route = Screen.MetricsScreen.route,
        ) {
            MetricsScreen(navController = navController)
        }
    }
}