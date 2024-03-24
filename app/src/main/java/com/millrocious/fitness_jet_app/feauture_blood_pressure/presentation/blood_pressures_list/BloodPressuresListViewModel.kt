package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.BloodPressureUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodPressuresListViewModel @Inject constructor(
    private val bloodPressureUseCases: BloodPressureUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BloodPressuresListState())
    val state: State<BloodPressuresListState> = _state

    private var recentlyDeletedBloodPressure: BloodPressure? = null

    private var getBloodPressuresJob: Job? = null

    init {
        fetchGroupedHeartRates()
    }

    fun onEvent(event: BloodPressuresListEvent) {
        when (event) {
            is BloodPressuresListEvent.DeleteBloodPressure -> {
                viewModelScope.launch {
                    bloodPressureUseCases.deleteBloodPressure(event.bloodPressure)
                    recentlyDeletedBloodPressure = event.bloodPressure
                }
            }

            is BloodPressuresListEvent.RestoreBloodPressure -> {
                viewModelScope.launch {
                    recentlyDeletedBloodPressure?.let {
                        bloodPressureUseCases.addBloodPressure(
                            recentlyDeletedBloodPressure ?: return@launch
                        )
                        recentlyDeletedBloodPressure = null
                    }
                }
            }
        }
    }

    private fun fetchGroupedHeartRates() {
        getBloodPressuresJob?.cancel()
        getBloodPressuresJob = bloodPressureUseCases.getBloodPressuresGroupedByDate()
            .onEach { bloodPressuresGrouped ->
                _state.value = _state.value.copy(
                    bloodPressuresGroupedByDate = bloodPressuresGrouped
                )
            }
            .launchIn(viewModelScope)
    }
}