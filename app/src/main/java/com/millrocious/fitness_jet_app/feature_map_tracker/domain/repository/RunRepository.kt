package com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

interface RunRepository {
    suspend fun insertRun(run: Run): Long
    suspend fun deleteRun(run: Run)
    suspend fun getRunById(id: Int): Run?
    fun getAllRun(): Flow<List<Run>>
}