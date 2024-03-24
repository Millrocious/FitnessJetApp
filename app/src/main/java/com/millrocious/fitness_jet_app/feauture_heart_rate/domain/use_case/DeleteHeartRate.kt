package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository

class DeleteHeartRate(
    private val repository: HeartRateRepository
) {
    suspend operator fun invoke(heartRate: HeartRate){
        repository.deleteHeartRate(heartRate)
    }
}