package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationServiceManager: LocationServiceManager,
    private val locationUseCases: LocationUseCases
): ViewModel() {
    private val _state = mutableStateOf(MapState())
    val state: State<MapState> = _state

    private var getLatestLocation: Job? = null

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.StartLocationService -> {
                locationServiceManager.startLocationService()
                fetchLatestLocation()
            }

            is MapEvent.StopLocationService -> {
                if (locationServiceManager.isLocationServiceRunning()) {
                    locationServiceManager.stopLocationService()
                    getLatestLocation?.cancel()
                }
            }

            is MapEvent.AddPathPoint -> {
                _state.value = _state.value.copy(
                    pathPoints = _state.value.pathPoints + event.latLng
                )
            }

            is MapEvent.ClearPathPoints -> {
                _state.value = _state.value.copy(
                    pathPoints = emptyList()
                )
            }
        }
    }

    private fun fetchLatestLocation() {
        getLatestLocation?.cancel()
        getLatestLocation = locationUseCases.getCurrentLocation()
            .onEach { location ->
                _state.value = _state.value.copy(
                    lastKnownLocation = location
                )
                if (location != null) {
                    onEvent(MapEvent.AddPathPoint(LatLng(location.latitude, location.longitude)))
                }
            }
            .launchIn(viewModelScope)
    }
}