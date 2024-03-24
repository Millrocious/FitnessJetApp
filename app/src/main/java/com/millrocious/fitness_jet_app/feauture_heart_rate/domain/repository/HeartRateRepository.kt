package com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository

import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HeartRateRepository {
    suspend fun insertHeartRate(heartRate: HeartRate)
    suspend fun deleteHeartRate(heartRate: HeartRate)
    suspend fun getHeartRateById(id: Int): HeartRate?
    fun getAllHeartRate(): Flow<List<HeartRate>>
    fun getHeartRateGroupedByDate(): Flow<Map<LocalDate, List<HeartRate>>>
    suspend fun updateHeartRate(heartRate: HeartRate)
}