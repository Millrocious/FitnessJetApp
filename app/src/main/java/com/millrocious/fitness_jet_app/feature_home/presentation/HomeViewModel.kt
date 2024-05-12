package com.millrocious.fitness_jet_app.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.HomeUseCases
import com.millrocious.fitness_jet_app.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    private val profileUseCases: UserUseCases
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private var getRunsJob: Job? = null
    private var getUserInfoJob: Job? = null
    private var getTotalStepsJob: Job? = null
    private var getTotalBurnedCaloriesJob: Job? = null

    init {
        fetchAllRuns()
        fetchUserInfo()
        fetchAllStepsByToday()
        fetchAllBurnedCaloriesByToday()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {

            else -> {}
        }
    }

    private fun fetchAllRuns() {
        getRunsJob?.cancel()
        getRunsJob = homeUseCases.getAllRun()
            .onEach { runs ->
                _state.value = _state.value.copy(
                    runList = runs
                )
            }
            .launchIn(viewModelScope)
    }

    private fun fetchAllStepsByToday() {
        getTotalStepsJob?.cancel()
        getTotalStepsJob = homeUseCases.getAllStepsByToday()
            .onEach { totalSteps ->
                _state.value = _state.value.copy(
                    totalSteps = totalSteps
                )
            }
            .launchIn(viewModelScope)
    }

    private fun fetchAllBurnedCaloriesByToday() {
        getTotalBurnedCaloriesJob?.cancel()
        getTotalBurnedCaloriesJob = homeUseCases.getAllBurnedCaloriesByToday()
            .onEach { totalBurnedCalories ->
                _state.value = _state.value.copy(
                    totalBurnedCalories = totalBurnedCalories
                )
            }
            .launchIn(viewModelScope)
    }

    private fun fetchUserInfo() {
        getUserInfoJob?.cancel()
        getUserInfoJob = profileUseCases.getUserInfo()
            .onEach { userInfo ->
                _state.value = _state.value.copy(
                    stepsGoal = userInfo.stepsGoal.toInt(),
                    burnedCaloriesGoal = userInfo.burnedCaloriesGoal.toInt()
                )
            }
            .launchIn(viewModelScope)
    }

}