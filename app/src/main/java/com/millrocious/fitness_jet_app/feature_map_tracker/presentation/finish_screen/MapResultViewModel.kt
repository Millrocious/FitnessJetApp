package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapResultViewModel @Inject constructor(
    private val locationUseCases: LocationUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(RunState())
    val state: State<RunState> = _state

    private var getLatestLocation: Job? = null

    private var currentRunId: String? = null

    init {
        savedStateHandle.get<String>("runId")?.let { runId ->
            if (runId != "") {
                viewModelScope.launch {
                    locationUseCases.getRunById(runId)?.also {
                        currentRunId = it.uuid

                        _state.value.img = it.img
                        _state.value.steps = it.steps
                        _state.value.avgSpeedInKMH = it.avgSpeedInKMH
                        _state.value.distanceInMeters = it.distanceInMeters
                        _state.value.durationInMillis = it.durationInMillis
                        _state.value.caloriesBurned = it.caloriesBurned
                    }
                }
            }
        }
    }

//    fun onEvent(event: MapResultEvent) {
//        when (event) {
////            is MapEvent.StartLocationService -> {
////                locationServiceManager.startLocationService()
////                fetchLatestLocation()
////            }
//
//            is MapResultEvent.StopLocationService -> {
////                if (locationServiceManager.isLocationServiceRunning()) {
////                    locationServiceManager.stopLocationService()
////                    getLatestLocation?.cancel()
////                }
//                locationUseCases.stopTracking()
//            }
//
////            is MapEvent.AddPathPoint -> {
////                _state.value = _state.value.copy(
////                    pathPoints = _state.value.pathPoints + event.latLng
////                )
////            }
////
////            is MapEvent.ClearPathPoints -> {
////                _state.value = _state.value.copy(
////                    pathPoints = emptyList()
////                )
////            }
//
//            is MapResultEvent.ResumePauseTracking -> {
//                locationUseCases.resumePauseTracking(currentRunState.value.isTracking)
//            }
//
//            is MapResultEvent.FinishRun -> {
//                locationUseCases.resumePauseTracking(true)
//
//                viewModelScope.launch {
//                    locationUseCases.addCurrentRun(
//                        Run(
//                            img = event.bitmap,
//                            avgSpeedInKMH = currentRunState.value.distanceInMeters
//                                .toBigDecimal()
//                                .multiply(3600.toBigDecimal())
//                                .divide(currentRunState.value.timeDuration.toBigDecimal(), 2, RoundingMode.HALF_UP)
//                                .toFloat(),
//                            distanceInMeters = currentRunState.value.distanceInMeters,
//                            durationInMillis = currentRunState.value.timeDuration,
//                            steps = currentRunState.value.steps
//                        )
//                    )
//                }
//
//                locationUseCases.stopTracking()
//            }
//        }
//    }

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