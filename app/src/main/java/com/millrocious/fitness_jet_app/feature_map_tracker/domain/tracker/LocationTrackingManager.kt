package com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker

import com.google.android.gms.location.LocationCallback

interface LocationTrackingManager {
    fun registerCallback(locationCallback: LocationCallback)
    fun unRegisterCallback(locationCallback: LocationCallback)
}