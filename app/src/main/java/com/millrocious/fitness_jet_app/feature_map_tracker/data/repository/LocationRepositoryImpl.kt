package com.millrocious.fitness_jet_app.feature_map_tracker.data.repository

import android.location.Location
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.observer.LocationObserver
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationServiceManager: LocationServiceManager,
    private val locationObserver: LocationObserver
) : LocationRepository {

    override fun getLocationUpdates(): Flow<Location?> {
        if (!locationServiceManager.isLocationServiceRunning()) {
            return flowOf(null)
        }

        return locationObserver.locationFlow.map { it }
    }
}
