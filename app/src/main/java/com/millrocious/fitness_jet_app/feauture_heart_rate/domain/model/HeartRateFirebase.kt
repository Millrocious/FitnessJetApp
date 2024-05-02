package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class HeartRateFirebase(
    val heartBeats: String = "",
    val selectedTimestamp: String = "",
    val createdTimestamp: String = "",
    val updatedTimestamp: String = "",
) {
    // Add this no-argument constructor
    constructor() : this("", "", "", "")
}
