package com.millrocious.fitness_jet_app.feature_map_tracker.domain.location


import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>
    class LocationException(message: String): Exception()
}