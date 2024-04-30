package com.millrocious.fitness_jet_app.feature_map_tracker.domain.model

import com.google.android.gms.maps.model.LatLng
import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.UserSpeed

data class LocationPoint(
    val latLng: LatLng,
    val speed: UserSpeed
)