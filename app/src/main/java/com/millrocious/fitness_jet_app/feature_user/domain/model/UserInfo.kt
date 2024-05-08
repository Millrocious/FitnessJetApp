package com.millrocious.fitness_jet_app.feature_user.domain.model

data class UserInfo (
    val height: String = "0",
    val weight: String = "0",
    val gender: String = Gender.MALE.toString(),
    val age: String = "0",
    val stepsGoal: String = "5000"
)
