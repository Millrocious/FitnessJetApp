package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetHeartRatesGroupedByDate(
    private val heartRateRepository: HeartRateRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, List<HeartRate>>> {
        return heartRateRepository.getHeartRateGroupedByDate()
    }
}