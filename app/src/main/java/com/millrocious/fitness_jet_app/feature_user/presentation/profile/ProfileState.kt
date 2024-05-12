package com.millrocious.fitness_jet_app.feature_user.presentation.profile

import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender

data class ProfileState(
    val height: Int = 0,
    val weight: Float = 0f,
    val gender: Gender = Gender.MALE,
    val age: Int = 0,
    val stepsGoal: Int = 5000,
    val burnedCaloriesGoal: Int = 300,
)