package com.millrocious.fitness_jet_app.feature_user.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar.BottomBar
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.framework.google_client.GoogleAuthUiClient
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ActionSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.bmi.BMISection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.GoalsSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ProfileDialog
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ProfileHeaderSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.UserDetailsSection
import com.millrocious.fitness_jet_app.feature_user.presentation.sign_in.UserData
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    userData: UserData?,
    googleAuthUiClient: GoogleAuthUiClient,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val profileState by viewModel.state
    val dialogType = remember { mutableStateOf<DialogType?>(null) }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    if (dialogType.value != null) {
        ProfileDialog(
            dialogType = dialogType.value!!,
            initialValue = when (dialogType.value) {
                DialogType.Weight -> profileState.weight
                DialogType.Height -> profileState.height
                DialogType.Age -> profileState.age
                DialogType.StepsGoal -> profileState.stepsGoal
                null -> 0
            },
            onDismissRequest = { dialogType.value = null },
            onConfirmation = { selectedValue ->
                when (dialogType.value) {
                    DialogType.Weight -> viewModel.onEvent(ProfileEvent.UpdateWeight(selectedValue.toFloat()))
                    DialogType.Height -> viewModel.onEvent(ProfileEvent.UpdateHeight(selectedValue.toInt()))
                    DialogType.Age -> viewModel.onEvent(ProfileEvent.UpdateAge(selectedValue.toInt()))
                    DialogType.StepsGoal -> viewModel.onEvent(
                        ProfileEvent.UpdateStepsGoal(
                            selectedValue.toInt()
                        )
                    )

                    null -> {}
                }
                dialogType.value = null
            }
        )
    }

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {
            ProfileHeaderSection(
                userData = userData,
            )
            Spacer(modifier = Modifier.height(16.dp))
            BMISection(profileState = profileState)
            GoalsSection(
                profileState = profileState,
                onDialogChange = {
                    dialogType.value = it
                }
            )
            UserDetailsSection(
                userData = userData,
                profileState = profileState,
                onGenderChange = {
                    viewModel.onEvent(ProfileEvent.UpdateGender(it))
                },
                onDialogChange = {
                    dialogType.value = it
                }
            )
            ActionSection(
                onSignOut = {
                    coroutineScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                }
            )
        }
    }
}