package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRateStatistics
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.HeartRateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeartRatesViewModel @Inject constructor(
    private val heartRateUseCases: HeartRateUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HeartRatesState())
    val state: State<HeartRatesState> = _state

    private val _statistics = mutableStateOf(HeartRateStatistics(0.0, 0, 0))
    val statistics: State<HeartRateStatistics> = _statistics

    private var getStatisticsJob: Job? = null

    init {
        observeStatistics()
    }

    private fun observeStatistics() {
        getStatisticsJob?.cancel()
        getStatisticsJob = heartRateUseCases.calculateHeartRateStatisticsForToday()
            .onEach { statistics ->
                _statistics.value = _statistics.value.copy(
                    average = statistics.average,
                    minimum = statistics.minimum,
                    maximum = statistics.maximum
                )
            }
            .launchIn(viewModelScope)
    }
}