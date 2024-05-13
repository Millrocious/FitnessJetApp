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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.component.DefaultAnimatedShowAndHide
import com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar.BottomBar
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender
import com.millrocious.fitness_jet_app.feature_user.framework.google_client.GoogleAuthUiClient
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ActionSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.GoalsSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ProfileDialog
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.ProfileHeaderSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.UserDetailsSection
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.bmi.BMISection
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

    if (dialogType.value != null) {
        ProfileDialog(
            dialogType = dialogType.value!!,
            initialValue = when (dialogType.value) {
                DialogType.Weight -> profileState.weight
                DialogType.Height -> profileState.height
                DialogType.Age -> profileState.age
                DialogType.StepsGoal -> profileState.stepsGoal
                DialogType.CaloriesGoal -> profileState.burnedCaloriesGoal
                DialogType.Gender -> profileState.gender
                null -> 0
            },
            onDismissRequest = { dialogType.value = null },
            onConfirmation = { selectedValue ->
                when (dialogType.value) {
                    DialogType.Weight -> viewModel.onEvent(ProfileEvent.UpdateWeight(selectedValue as Float))
                    DialogType.Height -> viewModel.onEvent(ProfileEvent.UpdateHeight(selectedValue as Int))
                    DialogType.Age -> viewModel.onEvent(ProfileEvent.UpdateAge(selectedValue as Int))
                    DialogType.Gender -> viewModel.onEvent(ProfileEvent.UpdateGender(selectedValue as Gender))
                    DialogType.StepsGoal -> viewModel.onEvent(
                        ProfileEvent.UpdateStepsGoal(
                            selectedValue as Int
                        )
                    )
                    DialogType.CaloriesGoal -> viewModel.onEvent(
                        ProfileEvent.UpdateBurnedCaloriesGoal(
                            selectedValue as Int
                        )
                    )

                    null -> {}
                }
                dialogType.value = null
            }
        )
    }

    val bottomBarHeight = 48.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }


// connection to the nested scroll system and listen to the scroll
// happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val scrollState = rememberScrollState()
    val shouldShowBottomBar by remember(scrollState) {
        derivedStateOf {
            val currentPosition = scrollState.value
            currentPosition == 0 // Check if the scroll position is at the top (0)
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        bottomBar = {
            DefaultAnimatedShowAndHide(shouldShowBottomBar) {
                BottomBar(
                    modifier = Modifier.padding(top = 20.dp),
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = paddingValues.calculateTopPadding()),
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

