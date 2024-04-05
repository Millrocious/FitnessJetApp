package com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.client


import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(): Flow<Location>
    class LocationException(message: String): Exception()
}