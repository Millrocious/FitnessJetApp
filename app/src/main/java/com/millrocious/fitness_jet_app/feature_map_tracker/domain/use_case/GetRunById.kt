package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository

class GetRunById(
    private val runRepository: RunRepository
) {
    suspend operator fun invoke(id: Int): Run? {
        return runRepository.getRunById(id)
    }
}