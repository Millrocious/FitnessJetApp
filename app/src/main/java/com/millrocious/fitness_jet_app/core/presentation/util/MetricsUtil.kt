package com.millrocious.fitness_jet_app.core.presentation.util

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.TextStyle
import java.util.Locale
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

    fun formatTimestamp(run: Run, timestampFormat: TimestampFormat): String {
        return when (timestampFormat) {
            TimestampFormat.MonthDayYearWithTime -> run.timestamp.formatWithTime()
        }
    }

    private fun OffsetDateTime.formatWithTime(): String {
        return "${month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)} ${dayOfMonth}, $year - ${toLocalTime().formatTime()}"
    }

    private fun LocalTime.formatTime(): String {
        return "%02d:%02d".format(hour, minute)
    }

    enum class TimestampFormat {
        MonthDayYearWithTime
    }
}