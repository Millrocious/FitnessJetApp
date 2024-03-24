package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model

data class BloodPressureStatistics(
    val averageSystolic: Double,
    val minimumSystolic: Int,
    val maximumSystolic: Int,
    val averageDiastolic: Double,
    val minimumDiastolic: Int,
    val maximumDiastolic: Int
)