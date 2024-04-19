package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.util

import java.util.concurrent.TimeUnit

object MetricsUtil {
    fun formatTime(timeDuration: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(timeDuration),
            TimeUnit.MILLISECONDS.toMinutes(timeDuration) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(timeDuration) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

    fun formatDistance(distanceInMeters: Int): String {
        return "%.2f km".format(distanceInMeters / 1000f)
    }

    fun formatSpeed(speedInKMH: Float): String {
        return "%.2f Km/H".format(speedInKMH)
    }
}