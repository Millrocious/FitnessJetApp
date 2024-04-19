package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager

class StartResumePauseTracking(
    private val trackingManager: TrackingManager
) {
    operator fun invoke(isTracking: Boolean) {
        if (isTracking) trackingManager.pauseTracking()
        else trackingManager.startResumeTracking()
    }
}