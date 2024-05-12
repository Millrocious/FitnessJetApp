package com.millrocious.fitness_jet_app.feature_user.domain.model

sealed class DialogType {
    data object Weight : DialogType()
    data object Height : DialogType()
    data object Age : DialogType()
    data object StepsGoal : DialogType()
    data object CaloriesGoal : DialogType()
    data object Gender : DialogType()
}