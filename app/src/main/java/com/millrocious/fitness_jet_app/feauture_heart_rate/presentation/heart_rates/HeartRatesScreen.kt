package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.component.CategoryButton
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates.component.HeartRateCard

@Composable
fun HeartRatesScreen(
    navController: NavController,
    viewModel: HeartRatesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value.heartRatesGroupedByDate
    val statistics = viewModel.statistics.value

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HeartRateCard(date = state.keys.firstOrNull().toString(), statistics = statistics)
            Spacer(modifier = Modifier.height(16.dp))
            CategoryButton(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        navController.navigate(Screen.HeartRatesListScreen.route)
                    },
                iconShape = CircleShape,
                iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                iconTintColor = MaterialTheme.colorScheme.onErrorContainer,
                title = "All Records"
            )
        }
    }
}