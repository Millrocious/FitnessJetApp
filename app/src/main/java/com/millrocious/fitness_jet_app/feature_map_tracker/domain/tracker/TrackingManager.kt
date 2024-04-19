package com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import kotlinx.coroutines.flow.Flow

interface TrackingManager {
    fun getCurrentRunStateFlow(): Flow<CurrentRunState>
    fun startResumeTracking()
    fun pauseTracking()
    fun getTrackingDurationInMsFlow(): Flow<Long>
    fun stop()
}