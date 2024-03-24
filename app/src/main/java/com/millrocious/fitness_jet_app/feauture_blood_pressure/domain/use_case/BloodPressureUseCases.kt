package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

data class BloodPressureUseCases(
    val addBloodPressure: AddBloodPressure,
    val deleteBloodPressure: DeleteBloodPressure,
    val getBloodPressures: GetBloodPressures,
    val getBloodPressure: GetBloodPressure,
    val getBloodPressuresGroupedByDate: GetBloodPressuresGroupedByDate,
    val calculateBloodPressureStatistics: CalculateBloodPressureStatistics
)