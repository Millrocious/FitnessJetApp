package com.millrocious.fitness_jet_app.core.presentation.screen.metrics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.component.CategoryButton
import com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar.BottomBar
import com.millrocious.fitness_jet_app.core.presentation.util.Screen


@Composable
fun MetricsScreen(
    navController: NavController,
) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        MetricsContent(
            modifier = Modifier.padding(paddingValues),
            onNavigate = { route ->
                navController.navigate(route)
            }
        )
    }


}

@Composable
fun MetricsContent(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(15.dp)
    ) {
        CategoryButton(
            modifier = Modifier.clickable {
                onNavigate(Screen.HeartRatesScreen.route)
            },
            iconShape = CircleShape,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
            iconTintColor = MaterialTheme.colorScheme.onErrorContainer,
            title = "Heart Rates"
        )
        Spacer(modifier = Modifier.height(15.dp))
        CategoryButton(
            modifier = Modifier.clickable {
                onNavigate(Screen.BloodPressuresScreen.route)
            },
            iconShape = CircleShape,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
            iconTintColor = MaterialTheme.colorScheme.onErrorContainer,
            title = "Blood Pressures"
        )
    }
}

@Preview
@Composable
private fun MetricsScreen() {
    MetricsContent(onNavigate = {})
}

