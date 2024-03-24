package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressureStatistics
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateBloodPressureStatistics(
    private val repository: BloodPressureRepository
) {
    operator fun invoke(): Flow<BloodPressureStatistics> {
        return repository.getBloodPressuresByDate()
            .map { groupedBloodPressures ->
                val firstNewestDate = groupedBloodPressures.keys.firstOrNull()

                if (firstNewestDate != null) {
                    val bloodPressuresForDate = groupedBloodPressures[firstNewestDate] ?: emptyList()
                    calculateStatistics(bloodPressuresForDate)
                } else {
                    BloodPressureStatistics(
                        0.0,
                        0,
                        0,
                        0.0,
                        0,
                        0
                    )
                }
            }
    }

    private fun calculateStatistics(bloodPressures: List<BloodPressure>): BloodPressureStatistics {
        val systolicValues = bloodPressures.map { it.systolic }
        val diastolicValues = bloodPressures.map { it.diastolic }

        val averageSystolic = systolicValues.average()
        val averageDiastolic = diastolicValues.average()

        val minimumSystolic = systolicValues.minOrNull() ?: 0
        val minimumDiastolic = diastolicValues.minOrNull() ?: 0

        val maximumSystolic = systolicValues.maxOrNull() ?: 0
        val maximumDiastolic = diastolicValues.maxOrNull() ?: 0

        return BloodPressureStatistics(
            averageSystolic,
            minimumSystolic,
            maximumSystolic,
            averageDiastolic,
            minimumDiastolic,
            maximumDiastolic
        )
    }
}