package com.millrocious.fitness_jet_app.feature_home.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class GetAllStepsByToday(
    private val runRepository: RunRepository
) {
    operator fun invoke(): Flow<Long?> {
        return runRepository.getTotalStepsByToday()
    }
}