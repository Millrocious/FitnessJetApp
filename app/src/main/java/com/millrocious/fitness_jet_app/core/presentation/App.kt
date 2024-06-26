package com.millrocious.fitness_jet_app.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.millrocious.fitness_jet_app.core.presentation.screen.metrics.MetricsScreen
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feature_home.presentation.HomeScreen
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.MapResultScreen
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.MapScreen
import com.millrocious.fitness_jet_app.feature_user.framework.google_client.GoogleAuthUiClient
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.ProfileScreen
import com.millrocious.fitness_jet_app.feature_user.presentation.sign_in.SignInScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure.AddEditBloodPressureScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures.BloodPressuresScreen
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list.BloodPressuresListScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate.AddEditHeartRateScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates.HeartRatesScreen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list.HeartRatesListScreen


@Composable
fun App(
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignInScreen.route
    ) {
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
                    type = NavType.StringType
                    defaultValue = ""
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
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            AddEditBloodPressureScreen(navController = navController)
        }

        // Metrics
        composable(
            route = Screen.MetricsScreen.route,
        ) {
            MetricsScreen(navController = navController)
        }

        // Map Screens
        composable(
            route = Screen.MapScreen.route,
        ) {
            MapScreen(navController = navController)
        }
        composable(
            route = Screen.FinishMapScreen.route +
                    "?runId={runId}",
            arguments = listOf(
                navArgument(
                    name = "runId"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            MapResultScreen(navController = navController)
        }

        // Home Screens
        composable(
            route = Screen.HomeScreen.route,
        ) {
            HomeScreen(
                navController = navController,
                userData = googleAuthUiClient.getSignedInUser(),
            )
        }

        // ------- Auth --------------
        // Sign In
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(
                navController = navController,
                googleAuthUiClient
            )
        }

        // Profile
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                userData = googleAuthUiClient.getSignedInUser(),
                googleAuthUiClient = googleAuthUiClient
            )
        }
    }
}