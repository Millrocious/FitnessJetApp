package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

data class HeartRateUseCases(
    val addHeartRate: AddHeartRate,
    val deleteHeartRate: DeleteHeartRate,
    val getAllHeartRate: GetAllHeartRate,
    val getHeartRate: GetHeartRate,
    val getHeartRatesGroupedByDate: GetHeartRatesGroupedByDate,
    val calculateHeartRateStatisticsForToday: CalculateHeartRateStatisticsForToday
)