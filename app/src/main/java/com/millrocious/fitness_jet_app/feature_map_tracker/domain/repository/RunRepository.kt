package com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import kotlinx.coroutines.flow.Flow

interface RunRepository {
    suspend fun insertRun(run: Run)
    suspend fun deleteRun(run: Run)
    fun getAllRun(): Flow<List<Run>>
}