package com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun BottomBar(
    navController: NavController
) {
    val screens = listOf(
        BottomNavItem.MapScreen, BottomNavItem.Metrics
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    GlobalScope.launch(Dispatchers.Main) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            // Badge()
                        }
                    ) {
                        Icon(
                            imageVector = if (currentRoute == item.route)
                                item.selectedIcon
                            else
                                item.unselectedIcon,
                            contentDescription = item.label
                        )
                    }
                },

                label = { Text(item.label) }
            )
        }
    }
}