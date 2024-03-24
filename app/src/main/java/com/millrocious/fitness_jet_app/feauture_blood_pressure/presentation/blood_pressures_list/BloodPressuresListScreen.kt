package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list

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
import kotlinx.coroutines.launch

@Composable
fun BloodPressuresListScreen(
    navController: NavController,
    viewModel: BloodPressuresListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value.bloodPressuresGroupedByDate
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditBloodPressureScreen.route)
                }
            ) {
                Text(
                    text = "Add new record"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            BloodPressureList(
                state,
                onNavigate = { route ->
                    navController.navigate(route)
                },
                onDelete = { bloodPressure ->
                    viewModel.onEvent(BloodPressuresListEvent.DeleteBloodPressure(bloodPressure))
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Note deleted",
                            actionLabel = "Undo"
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(BloodPressuresListEvent.RestoreBloodPressure)
                        }
                    }
                }
            )
//            Card(
//                modifier = Modifier
//                    .padding(15.dp)
//                    .fillMaxWidth(),
//                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer),
//                shape = RoundedCornerShape(25.dp)
//            ) {
//                Row(
//                    modifier = Modifier.padding(20.dp),
//                ) {
//                    IconBox(
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.errorContainer),
//                        tintColor = MaterialTheme.colorScheme.onErrorContainer
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 20.dp),
//                        text = "All records",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 24.sp,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//            }

        }
    }
}