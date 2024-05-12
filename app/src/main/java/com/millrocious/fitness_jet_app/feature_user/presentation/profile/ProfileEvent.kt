package com.millrocious.fitness_jet_app.feature_user.presentation.profile

import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender

sealed class ProfileEvent {
    data class UpdateHeight(val height: Int) : ProfileEvent()
    data class UpdateWeight(val weight: Float) : ProfileEvent()
    data class UpdateAge(val age: Int) : ProfileEvent()
    data class UpdateGender(val gender: Gender) : ProfileEvent()
    data class UpdateStepsGoal(val stepsGoal: Int) : ProfileEvent()
    data class UpdateBurnedCaloriesGoal(val burnedCaloriesGoal: Int) : ProfileEvent()
}