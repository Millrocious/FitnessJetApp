package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository

class DeleteBloodPressure(
    private val repository: BloodPressureRepository
) {
    suspend operator fun invoke(bloodPressure: BloodPressure){
        repository.deleteBloodPressure(bloodPressure)
    }
}