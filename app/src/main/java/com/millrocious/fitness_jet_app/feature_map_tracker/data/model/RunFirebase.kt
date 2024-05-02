package com.millrocious.fitness_jet_app.feature_map_tracker.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RunFirebase(
    var img: String = "",
    var steps: String = "0",
    var avgSpeedInKMH: String = "0",
    var distanceInMeters: String = "0",
    var durationInMillis: String = "0",
    var timestamp: String = "",
)