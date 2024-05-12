package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunStateWithCalories
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlin.math.roundToInt

class GetCurrentRunStateWithCalories(
    private val trackingManager: TrackingManager,
    private val userRepository: UserInfoRepository
) {
    operator fun invoke(): Flow<CurrentRunStateWithCalories> {
        return combine(userRepository.getUserProfileFlow(), trackingManager.getCurrentRunStateFlow()) { user, runState ->
            CurrentRunStateWithCalories(
                currentRunState = runState,
                caloriesBurnt = RunUtils.calculateCaloriesBurnt(
                    distanceInMeters = runState.distanceInMeters,
                    weightInKg = user.weight.toFloat()
                ).roundToInt()
            )
        }
    }
}