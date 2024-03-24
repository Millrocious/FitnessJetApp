package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import java.util.UUID

@Entity
data class HeartRate(
    @PrimaryKey val id: Int? = null,
    val heartBeats: Int,
    val selectedTimestamp: OffsetDateTime,
    val createdTimestamp: OffsetDateTime = OffsetDateTime.now(),
    val updatedTimestamp: OffsetDateTime = OffsetDateTime.now(),
    val uuid: String = UUID.randomUUID().toString()
)
