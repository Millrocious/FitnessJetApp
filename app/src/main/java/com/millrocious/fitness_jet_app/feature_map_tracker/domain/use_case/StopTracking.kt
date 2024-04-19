package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager

class StopTracking(
    private val trackingManager: TrackingManager
) {
    operator fun invoke() {
        trackingManager.stop()
    }
}