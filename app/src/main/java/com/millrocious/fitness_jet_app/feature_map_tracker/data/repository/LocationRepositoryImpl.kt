package com.millrocious.fitness_jet_app.feature_map_tracker.data.repository

import android.location.Location
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.client.LocationClient
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationClient: LocationClient
) : LocationRepository {

    override fun getLocationUpdates(): Flow<Location> {
        return locationClient.getLocationUpdates()
    }
}
