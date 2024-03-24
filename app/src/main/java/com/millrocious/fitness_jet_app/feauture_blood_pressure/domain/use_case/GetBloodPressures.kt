package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow

class GetBloodPressures(
    private val repository: BloodPressureRepository
) {
    operator fun invoke(): Flow<List<BloodPressure>> {
        return repository.getBloodPressures()
    }
}