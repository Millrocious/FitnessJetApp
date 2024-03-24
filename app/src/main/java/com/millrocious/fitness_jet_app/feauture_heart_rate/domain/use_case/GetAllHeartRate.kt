package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import kotlinx.coroutines.flow.Flow

class GetAllHeartRate(
    private val repository: HeartRateRepository
) {
    operator fun invoke(): Flow<List<HeartRate>> {
        return repository.getAllHeartRate()
    }
}