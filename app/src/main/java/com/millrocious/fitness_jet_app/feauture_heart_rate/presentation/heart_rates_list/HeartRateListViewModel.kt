package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.HeartRateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeartRateListViewModel @Inject constructor(
    private val heartRateUseCases: HeartRateUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HeartRateListState())
    val state: State<HeartRateListState> = _state

    private var recentlyDeletedHeartRate: HeartRate? = null

    private var getHeartRatesJob: Job? = null
    private var getStatisticsJob: Job? = null

    init {
        fetchGroupedHeartRates()
    }

    fun onEvent(event: HeartRateListEvent) {
        when (event) {
            is HeartRateListEvent.DeleteHeartRate -> {
                viewModelScope.launch {
                    heartRateUseCases.deleteHeartRate(event.heartRate)
                    recentlyDeletedHeartRate = event.heartRate
                }
            }

            is HeartRateListEvent.RestoreHeartRate -> {
                viewModelScope.launch {
                    recentlyDeletedHeartRate?.let {
                        heartRateUseCases.addHeartRate(recentlyDeletedHeartRate ?: return@launch)
                        recentlyDeletedHeartRate = null
                    }
                }
            }
        }
    }

    private fun fetchGroupedHeartRates() {
        getHeartRatesJob?.cancel()
        getHeartRatesJob = heartRateUseCases.getHeartRatesGroupedByDate()
            .onEach { heartRatesGrouped ->
                _state.value = _state.value.copy(
                    heartRatesGroupedByDate = heartRatesGrouped
                )
            }
            .launchIn(viewModelScope)
    }
}