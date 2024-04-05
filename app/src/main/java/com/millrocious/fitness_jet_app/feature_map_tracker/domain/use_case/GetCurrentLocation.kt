package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import android.location.Location
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentLocation(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(): Flow<Location?> {
        return locationRepository.getLocationUpdates()
    }
}