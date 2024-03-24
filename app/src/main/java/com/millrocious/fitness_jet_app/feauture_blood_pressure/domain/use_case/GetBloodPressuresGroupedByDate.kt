package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetBloodPressuresGroupedByDate(
    private val heartRateRepository: BloodPressureRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, List<BloodPressure>>> {
        return heartRateRepository.getBloodPressuresByDate()
    }
}