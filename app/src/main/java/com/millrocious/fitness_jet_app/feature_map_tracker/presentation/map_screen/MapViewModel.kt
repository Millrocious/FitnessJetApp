package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationUseCases: LocationUseCases
): ViewModel() {
    val currentRunState = locationUseCases.getCurrentRunState().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        CurrentRunState()
    )

    private var currentRunId: String? = null
    private var onFinishRunCallback: ((String?) -> Unit)? = null

    private var getLatestLocation: Job? = null

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.StopLocationService -> {
                locationUseCases.stopTracking()
            }
            is MapEvent.ResumePauseTracking -> {
                locationUseCases.resumePauseTracking(currentRunState.value.isTracking)
            }

            is MapEvent.FinishRun -> {
                locationUseCases.resumePauseTracking(true)
                Log.d("MapViewModel", "Executing onEvent: $event")

                viewModelScope.launch {
                    currentRunId = locationUseCases.addCurrentRun(
                        Run(
                            img = event.bitmap,
                            avgSpeedInKMH = currentRunState.value.distanceInMeters
                                .toBigDecimal()
                                .multiply(3600.toBigDecimal())
                                .divide(currentRunState.value.timeDuration.toBigDecimal(), 2, RoundingMode.HALF_UP)
                                .toFloat(),
                            distanceInMeters = currentRunState.value.distanceInMeters,
                            durationInMillis = currentRunState.value.timeDuration,
                            steps = currentRunState.value.steps
                        )
                    )
                    Log.d("CurrentRunId2", currentRunId.toString())
                    Log.d("CurrentRunId2", onFinishRunCallback.toString())

                    onFinishRunCallback?.invoke(currentRunId)
                }

                locationUseCases.stopTracking()
            }
        }
    }

    fun finishRun(callback: (String?) -> Unit) {
        onFinishRunCallback = callback
        Log.d("MapViewModel", "finishRun callback set")
    }
}