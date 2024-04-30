package com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.millrocious.fitness_jet_app.core.presentation.util.Screen

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object MapScreen : BottomNavItem(
        Screen.MapScreen.route,
        "Track",
        Icons.Filled.LocationOn,
        Icons.Outlined.LocationOn
    )
    data object Home : BottomNavItem(
        Screen.HomeScreen.route,
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home
    )

    data object Metrics : BottomNavItem(
        Screen.MetricsScreen.route,
        "Metrics",
        Icons.AutoMirrored.Filled.List,
        Icons.AutoMirrored.Outlined.List
    )
//    data object HeartRatesScreen: BottomNavItem(
//        Screen.HeartRatesScreen.route,
//        "Your heart",
//        Icons.Filled.Favorite,
//        Icons.Filled.FavoriteBorder
//    )
//    data object BloodPressuresScreen: BottomNavItem(
//        Screen.BloodPressuresScreen.route,
//        "Your blood pressure",
//        Icons.Filled.Favorite,
//        Icons.Filled.FavoriteBorder
//    )
}