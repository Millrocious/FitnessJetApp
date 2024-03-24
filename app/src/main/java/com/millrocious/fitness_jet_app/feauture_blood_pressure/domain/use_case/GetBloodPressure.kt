package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository

class GetBloodPressure(
    private val heartRateRepository: BloodPressureRepository
) {
    suspend operator fun invoke(id: Int): BloodPressure? {
        return heartRateRepository.getBloodPressureById(id)
    }
}