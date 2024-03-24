package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure

import java.time.OffsetDateTime

sealed class AddEditBloodPressureEvent {
    data class UpdateBloodPressureSystolic(val value: Int) : AddEditBloodPressureEvent()
    data class UpdateBloodPressureDiastolic(val value: Int) : AddEditBloodPressureEvent()
    data class UpdateTimestamp(val value: OffsetDateTime) : AddEditBloodPressureEvent()

    object SaveBloodPressure : AddEditBloodPressureEvent()
}