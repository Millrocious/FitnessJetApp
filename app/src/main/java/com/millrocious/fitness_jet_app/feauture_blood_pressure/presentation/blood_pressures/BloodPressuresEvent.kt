package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure

sealed class BloodPressuresEvent {
    data class DeleteBloodPressure(val bloodPressure: BloodPressure) : BloodPressuresEvent()
    object RestoreBloodPressure : BloodPressuresEvent()
}