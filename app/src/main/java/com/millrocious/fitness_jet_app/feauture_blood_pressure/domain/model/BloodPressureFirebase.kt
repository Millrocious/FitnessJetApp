package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class BloodPressureFirebase(
    val systolic: String = "",
    val diastolic: String = "",
    val selectedTimestamp: String = "",
    val createdTimestamp: String = "",
    val updatedTimestamp: String = ""
)
