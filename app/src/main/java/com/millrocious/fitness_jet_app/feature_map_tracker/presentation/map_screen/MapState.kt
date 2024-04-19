package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class MapState(
    val lastKnownLocation: Location? = null,
    val pathPoints: List<LatLng> = emptyList()
)