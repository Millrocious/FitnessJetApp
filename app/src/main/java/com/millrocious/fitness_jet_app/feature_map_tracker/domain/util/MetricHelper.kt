package com.millrocious.fitness_jet_app.feature_map_tracker.domain.util

import com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen.component.UserSpeed

object MetricHelper {
    fun getUserSpeedStateByValue(speed: Float): UserSpeed {
        return when (speed) {
            in 0f..0.1f -> UserSpeed.SLOW
            in 0.11f..2f -> UserSpeed.MEDIUM
            else -> UserSpeed.FAST
        }
    }
}