package com.millrocious.fitness_jet_app.feature_home.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class GetAllBurnedCaloriesByToday(
    private val runRepository: RunRepository
) {
    operator fun invoke(): Flow<Int?> {
        return runRepository.getTotalBurnedCaloriesByToday()
    }
}