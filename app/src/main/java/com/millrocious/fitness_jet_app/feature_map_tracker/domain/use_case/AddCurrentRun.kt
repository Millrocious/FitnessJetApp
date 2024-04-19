package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository

class AddCurrentRun(
    private val runRepository: RunRepository
) {
    suspend operator fun invoke(run: Run) {
        return runRepository.insertRun(run)
    }
}