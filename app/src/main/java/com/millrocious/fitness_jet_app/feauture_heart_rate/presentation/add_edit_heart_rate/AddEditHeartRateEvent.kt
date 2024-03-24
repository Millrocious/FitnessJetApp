package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate

import java.time.OffsetDateTime

sealed class AddEditHeartRateEvent {
    data class UpdateHeartRate(val value: Int) : AddEditHeartRateEvent()
    data class UpdateTimestamp(val value: OffsetDateTime) : AddEditHeartRateEvent()

    object SaveHeartRate : AddEditHeartRateEvent()
}