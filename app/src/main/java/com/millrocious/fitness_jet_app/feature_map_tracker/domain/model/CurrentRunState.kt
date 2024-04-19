package com.millrocious.fitness_jet_app.feature_map_tracker.domain.model

data class CurrentRunState(
    val distanceInMeters: Int = 0,
    val speedInKMH: Float = 0f,
    val isTracking: Boolean = false,
    val timeDuration: Long = 0L,
    val steps: Long = 0L,
    val pathPoints: List<PathPoint> = emptyList()
)