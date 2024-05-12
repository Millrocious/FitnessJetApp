package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list.component.HeartRateList
import kotlinx.coroutines.launch

@Composable
fun HeartRatesListScreen(
    navController: NavController,
    viewModel: HeartRateListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value.heartRatesGroupedByDate
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditHeartRateScreen.route)
                }
            ) {
                Text(
                    text = "Add new record"
                )
            }
        },
    ) { paddingValues ->
        Log.d("heartRate_list_state", state.toString())
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HeartRateList(
                state,
                onNavigate = { route ->
                    navController.navigate(route)
                }
            ) { heartRate ->
                viewModel.onEvent(HeartRateListEvent.DeleteHeartRate(heartRate))
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Heart rate deleted",
                        actionLabel = "Undo"
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(HeartRateListEvent.RestoreHeartRate)
                    }
                }
            }
        }
    }
}