package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure.component.AddEditBloodPressureContent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBloodPressureScreen(
    navController: NavController,
    viewModel: AddEditBloodPressureViewModel = hiltViewModel(),
) {
    val bloodPressureSystolicState = viewModel.bloodPressureSystolic.value
    val bloodPressureDiastolicState = viewModel.bloodPressureDiastolic.value

    val selectedTimestamp = viewModel.selectedTimestamp.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditBloodPressureViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditBloodPressureViewModel.UiEvent.SaveHeartRate -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (viewModel.getCurrentHeartRateId() != null)
                            "Edit blood pressure"
                        else
                            "Add blood pressure"
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.onEvent(AddEditBloodPressureEvent.SaveBloodPressure)
                        }
                    ) {
                        Text(text = "Save")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        AddEditBloodPressureContent(
            modifier = Modifier.padding(padding),
            bloodPressureSystolicState = bloodPressureSystolicState,
            bloodPressureDiastolicState = bloodPressureDiastolicState,
            selectedTimestamp = selectedTimestamp,
            onSystolicChange = { systolic ->
                viewModel.onEvent(AddEditBloodPressureEvent.UpdateBloodPressureSystolic(systolic))
            },
            onDiastolicChange = { diastolic ->
                viewModel.onEvent(AddEditBloodPressureEvent.UpdateBloodPressureDiastolic(diastolic))
            },
            onTimestampChange = { timestamp ->
                viewModel.onEvent(AddEditBloodPressureEvent.UpdateTimestamp(timestamp))
            }
        )
    }
}