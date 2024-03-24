package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure

sealed class BloodPressuresListEvent {
    data class DeleteBloodPressure(val bloodPressure: BloodPressure) : BloodPressuresListEvent()
    object RestoreBloodPressure : BloodPressuresListEvent()
}