package com.millrocious.fitness_jet_app.core.domain.model

data class DateCategory<T>(
    val date: String,
    val items: List<T>
)