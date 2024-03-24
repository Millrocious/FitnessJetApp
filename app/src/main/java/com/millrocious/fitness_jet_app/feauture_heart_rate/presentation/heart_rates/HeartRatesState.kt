package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import java.time.LocalDate

data class HeartRatesState(
    val heartRatesGroupedByDate: Map<LocalDate, List<HeartRate>> = emptyMap(),
)
