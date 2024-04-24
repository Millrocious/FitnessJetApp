package com.millrocious.fitness_jet_app.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private var getRunsJob: Job? = null
    private var getTotalStepsJob: Job? = null

    init {
        fetchAllRuns()
        fetchAllStepsByToday()
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

}