package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import kotlinx.coroutines.flow.Flow

class GetCurrentRunState(
    private val trackingManager: TrackingManager
) {
    operator fun invoke(): Flow<CurrentRunState> {
        return trackingManager.getCurrentRunStateFlow()
    }
}