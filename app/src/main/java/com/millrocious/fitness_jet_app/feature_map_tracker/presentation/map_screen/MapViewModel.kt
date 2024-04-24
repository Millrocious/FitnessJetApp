package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

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
//    private val _state = mutableStateOf(MapState())
//    val state: State<MapState> = _state

    val currentRunState = locationUseCases.getCurrentRunState().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        CurrentRunState()
    )

    private var currentRunId: Long? = null
    private var onFinishRunCallback: ((Long?) -> Unit)? = null

    private var getLatestLocation: Job? = null

    fun onEvent(event: MapEvent) {
        when (event) {
//            is MapEvent.StartLocationService -> {
//                locationServiceManager.startLocationService()
//                fetchLatestLocation()
//            }

            is MapEvent.StopLocationService -> {
//                if (locationServiceManager.isLocationServiceRunning()) {
//                    locationServiceManager.stopLocationService()
//                    getLatestLocation?.cancel()
//                }
                locationUseCases.stopTracking()
            }

//            is MapEvent.AddPathPoint -> {
//                _state.value = _state.value.copy(
//                    pathPoints = _state.value.pathPoints + event.latLng
//                )
//            }
//
//            is MapEvent.ClearPathPoints -> {
//                _state.value = _state.value.copy(
//                    pathPoints = emptyList()
//                )
//            }

            is MapEvent.ResumePauseTracking -> {
                locationUseCases.resumePauseTracking(currentRunState.value.isTracking)
            }

            is MapEvent.FinishRun -> {
                locationUseCases.resumePauseTracking(true)

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

                    onFinishRunCallback?.invoke(currentRunId)
                }

                locationUseCases.stopTracking()
            }
        }
    }

    fun finishRun(callback: (Long?) -> Unit) {
        onFinishRunCallback = callback
    }

//    private fun fetchLatestLocation() {
//        getLatestLocation?.cancel()
//        getLatestLocation = locationUseCases.getCurrentLocation()
//            .onEach { location ->
//                _state.value = _state.value.copy(
//                    lastKnownLocation = location
//                )
//                if (location != null) {
//                    onEvent(MapEvent.AddPathPoint(LatLng(location.latitude, location.longitude)))
//                }
//            }
//            .launchIn(viewModelScope)
//    }
}