package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import java.time.LocalDate

data class HeartRateListState(
    val heartRatesGroupedByDate: Map<LocalDate, List<HeartRate>> = emptyMap(),
)
