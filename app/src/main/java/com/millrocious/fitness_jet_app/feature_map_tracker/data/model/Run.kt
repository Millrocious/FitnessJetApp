package com.millrocious.fitness_jet_app.feature_map_tracker.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "running_table")
data class Run(
    @PrimaryKey val id: Int? = null,
    var img: Bitmap,
    var steps: Long = 0,
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var durationInMillis: Long = 0L,
    var timestamp: OffsetDateTime = OffsetDateTime.now(),
)