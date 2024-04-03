package com.millrocious.fitness_jet_app.feature_map_tracker.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.manager.DefaultLocationServiceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationServiceManager: DefaultLocationServiceManager
): ViewModel() {
    val state: MutableState<MapState> = mutableStateOf(
        MapState(lastKnownLocation = null)
    )

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.StartLocationService -> {
                locationServiceManager.startLocationService()


            }

            is MapEvent.StopLocationService -> {
                locationServiceManager.stopLocationService()
            }
        }
    }
}