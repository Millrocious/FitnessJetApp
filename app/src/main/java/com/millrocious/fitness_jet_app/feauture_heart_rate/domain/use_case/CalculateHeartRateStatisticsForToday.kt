package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRateStatistics
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateHeartRateStatisticsForToday(
    private val repository: HeartRateRepository
) {
    operator fun invoke(): Flow<HeartRateStatistics> {
        return repository.getHeartRateGroupedByDate()
            .map { groupedHeartRates ->
                val firstNewestDate = groupedHeartRates.keys.firstOrNull()

                if (firstNewestDate != null) {
                    val heartRatesForDate = groupedHeartRates[firstNewestDate] ?: emptyList()
                    calculateStatistics(heartRatesForDate)
                } else {
                    HeartRateStatistics(0.0, 0, 0)
                }
            }
    }

    private fun calculateStatistics(heartRates: List<HeartRate>): HeartRateStatistics {
        val average = heartRates.map { it.heartBeats.toDouble() }.average()
        val minimum = heartRates.minByOrNull { it.heartBeats }!!.heartBeats
        val maximum = heartRates.maxByOrNull { it.heartBeats }!!.heartBeats

        return HeartRateStatistics(average, minimum, maximum)
    }
}