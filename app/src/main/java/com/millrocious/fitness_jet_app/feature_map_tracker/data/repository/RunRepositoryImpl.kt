package com.millrocious.fitness_jet_app.feature_map_tracker.data.repository

import com.millrocious.fitness_jet_app.feature_map_tracker.data.dao.RunDao
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RunRepositoryImpl @Inject constructor(
    private val runDao: RunDao
) : RunRepository {
    override suspend fun insertRun(run: Run): String {
        runDao.insertRun(run)

        return run.uuid
    }

    override suspend fun deleteRun(run: Run) {
        runDao.deleteRun(run)
    }

    override suspend fun getRunById(id: String): Run? {
        return runDao.getRunById(id)
    }

    override fun getAllRun(): Flow<List<Run>> {
        return runDao.getAllRun()
    }

    override fun getTotalStepsByToday(): Flow<Long?> {
        return runDao.getTotalStepsByToday()
    }
}
