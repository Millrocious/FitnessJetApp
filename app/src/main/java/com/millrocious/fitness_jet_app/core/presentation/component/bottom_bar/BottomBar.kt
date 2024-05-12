package com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
        BottomNavItem.MapScreen, BottomNavItem.Home, BottomNavItem.Metrics, BottomNavItem.Profile
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        Alignment.BottomCenter
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(80.dp).padding(top = 25.dp).background(MaterialTheme.colorScheme.background))
        NavigationBar(
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
                .clip(RoundedCornerShape(20.dp)),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            tonalElevation = 0.dp
        ) {
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
}