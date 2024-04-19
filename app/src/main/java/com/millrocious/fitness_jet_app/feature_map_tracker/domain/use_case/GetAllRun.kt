package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class GetAllRun(
    private val runRepository: RunRepository
) {
    operator fun invoke(): Flow<List<Run>> {
        return runRepository.getAllRun()
    }
}