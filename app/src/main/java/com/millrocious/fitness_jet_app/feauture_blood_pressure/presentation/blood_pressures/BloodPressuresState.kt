package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import java.time.LocalDate

data class BloodPressuresState(
    val bloodPressuresGroupedByDate: Map<LocalDate, List<BloodPressure>> = emptyMap(),
)
