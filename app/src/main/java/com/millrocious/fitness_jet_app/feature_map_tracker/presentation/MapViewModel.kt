package com.millrocious.fitness_jet_app.feature_map_tracker.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(

): ViewModel() {
    val state: MutableState<MapState> = mutableStateOf(
        MapState(lastKnownLocation = null)
    )
}