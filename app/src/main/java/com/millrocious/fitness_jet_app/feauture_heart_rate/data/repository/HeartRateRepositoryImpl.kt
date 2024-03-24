package com.millrocious.fitness_jet_app.feauture_heart_rate.data.repository

import com.millrocious.fitness_jet_app.feauture_heart_rate.data.dao.HeartRateDao
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class HeartRateRepositoryImpl(
    private val dao: HeartRateDao
): HeartRateRepository {
    override suspend fun insertHeartRate(heartRate: HeartRate) {
        dao.insertHeartRate(heartRate)
    }

    override suspend fun deleteHeartRate(heartRate: HeartRate) {
        dao.deleteHeartRate(heartRate)
    }

    override suspend fun getHeartRateById(id: Int): HeartRate? {
        return dao.getHeartRateById(id)
    }

    override fun getAllHeartRate(): Flow<List<HeartRate>> {
        return dao.getAllHeartRate()
    }

    override fun getHeartRateGroupedByDate(): Flow<Map<LocalDate, List<HeartRate>>> {
        return dao.getAllHeartRate()
            .map { heartRates ->
                heartRates.groupBy { it.createdTimestamp.toLocalDate() }
                    .toSortedMap(compareByDescending { it })
            }
    }

    override suspend fun updateHeartRate(heartRate: HeartRate) {
        dao.updateHeartRate(heartRate)
    }
}