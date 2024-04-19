package com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.config

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority

object LocationConfig {
    private const val LOCATION_UPDATE_INTERVAL = 1000L

    val locationRequestBuilder
        get() = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            LOCATION_UPDATE_INTERVAL
        )
}