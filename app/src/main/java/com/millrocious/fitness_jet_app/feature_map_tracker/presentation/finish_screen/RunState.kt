package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen

import android.graphics.Bitmap
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

data class RunState (
    var img: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888),
    var steps: Long = 0,
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var durationInMillis: Long = 0L,
)