package com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository

import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BloodPressureRepository {
    suspend fun insertBloodPressure(bloodPressure: BloodPressure)
    suspend fun deleteBloodPressure(bloodPressure: BloodPressure)
    suspend fun getBloodPressureById(id: Int): BloodPressure?
    fun getBloodPressures(): Flow<List<BloodPressure>>
    fun getBloodPressuresByDate(): Flow<Map<LocalDate, List<BloodPressure>>>
    suspend fun updateBloodPressure(bloodPressure: BloodPressure)
}