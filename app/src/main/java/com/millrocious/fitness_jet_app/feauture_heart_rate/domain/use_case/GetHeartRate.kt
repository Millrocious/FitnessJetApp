package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository

class GetHeartRate(
    private val heartRateRepository: HeartRateRepository
) {
    suspend operator fun invoke(id: Int): HeartRate? {
        return heartRateRepository.getHeartRateById(id)
    }
}