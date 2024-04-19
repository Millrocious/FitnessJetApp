package com.millrocious.fitness_jet_app.feature_map_tracker.domain.model

import com.google.android.gms.maps.model.LatLng

sealed interface PathPoint {
    class LocationPoint(val latLng: LatLng) : PathPoint
    object EmptyLocationPoint : PathPoint
}