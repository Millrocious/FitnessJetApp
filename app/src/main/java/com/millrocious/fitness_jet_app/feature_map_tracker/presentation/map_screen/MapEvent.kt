package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object StartLocationService : MapEvent()
    object StopLocationService : MapEvent()
    data class AddPathPoint(val latLng: LatLng) : MapEvent()
    object ClearPathPoints : MapEvent()
}