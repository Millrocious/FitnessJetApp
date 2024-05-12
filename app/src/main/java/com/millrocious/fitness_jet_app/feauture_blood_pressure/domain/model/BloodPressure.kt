package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import java.util.UUID

@Entity
data class BloodPressure(
    val systolic: Int,
    val diastolic: Int,
    val selectedTimestamp: OffsetDateTime,
    val createdTimestamp: OffsetDateTime = OffsetDateTime.now(),
    val updatedTimestamp: OffsetDateTime = OffsetDateTime.now(),
    @PrimaryKey val uuid: String = UUID.randomUUID().toString()
)
