package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model

data class DateCategory<T>(
    val date: String,
    val items: List<T>
)