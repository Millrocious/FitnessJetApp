package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate

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
import com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate.component.AddEditHeartRateContent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditHeartRateScreen(
    navController: NavController,
    viewModel: AddEditHeartRateViewModel = hiltViewModel(),
) {
    val heartRateState = viewModel.heartRate.value
    val selectedTimestamp = viewModel.selectedTimestamp.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditHeartRateViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditHeartRateViewModel.UiEvent.SaveHeartRate -> {
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
                            "Edit heart rate"
                        else
                            "Add heart rate"
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.onEvent(AddEditHeartRateEvent.SaveHeartRate)
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
        AddEditHeartRateContent(
            modifier = Modifier.padding(padding),
            heartRateState = heartRateState,
            selectedTimestamp = selectedTimestamp,
            onValueChange = { heartRateValue ->
                viewModel.onEvent(AddEditHeartRateEvent.UpdateHeartRate(heartRateValue))
            },
            onTimestampChange = { timestamp ->
                viewModel.onEvent(AddEditHeartRateEvent.UpdateTimestamp(timestamp))
            }
        )
    }
}