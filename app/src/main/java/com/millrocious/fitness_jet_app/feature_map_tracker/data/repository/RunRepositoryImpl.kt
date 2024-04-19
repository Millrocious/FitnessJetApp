package com.millrocious.fitness_jet_app.feature_map_tracker.data.repository

import com.millrocious.fitness_jet_app.feature_map_tracker.data.dao.RunDao
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RunRepositoryImpl @Inject constructor(
    private val runDao: RunDao
) : RunRepository {
    override suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }

    override suspend fun deleteRun(run: Run) {
        runDao.deleteRun(run)
    }

    override fun getAllRun(): Flow<List<Run>> {
        return runDao.getAllRun()
    }
}
