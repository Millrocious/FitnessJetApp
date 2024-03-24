package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate

sealed class HeartRatesEvent {
    data class DeleteHeartRate(val heartRate: HeartRate) : HeartRatesEvent()
    object RestoreHeartRate : HeartRatesEvent()
}