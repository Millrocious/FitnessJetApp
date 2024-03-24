package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate

sealed class HeartRateListEvent {
    data class DeleteHeartRate(val heartRate: HeartRate) : HeartRateListEvent()
    object RestoreHeartRate : HeartRateListEvent()
}