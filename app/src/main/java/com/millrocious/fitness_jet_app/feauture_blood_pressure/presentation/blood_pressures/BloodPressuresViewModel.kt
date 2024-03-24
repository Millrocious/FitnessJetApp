package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressureStatistics
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.BloodPressureUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BloodPressuresViewModel @Inject constructor(
    private val bloodPressureUseCases: BloodPressureUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BloodPressuresState())
    val state: State<BloodPressuresState> = _state

    private val _statistics = mutableStateOf(BloodPressureStatistics(0.0, 0, 0, 0.0, 0, 0))
    val statistics: State<BloodPressureStatistics> = _statistics

    private var recentlyDeletedBloodPressure: BloodPressure? = null

    private var getBloodPressuresJob: Job? = null
    private var getStatisticsJob: Job? = null

    init {
        fetchGroupedHeartRates()
        observeStatistics()
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

    private fun observeStatistics() {
        getStatisticsJob?.cancel()
        getStatisticsJob = bloodPressureUseCases.calculateBloodPressureStatistics()
            .onEach { statistics ->
                _statistics.value = _statistics.value.copy(
                    averageSystolic = statistics.averageSystolic,
                    minimumSystolic = statistics.minimumSystolic,
                    maximumSystolic = statistics.maximumSystolic,
                    averageDiastolic = statistics.averageDiastolic,
                    minimumDiastolic = statistics.minimumDiastolic,
                    maximumDiastolic = statistics.maximumDiastolic
                )
            }
            .launchIn(viewModelScope)
    }
}