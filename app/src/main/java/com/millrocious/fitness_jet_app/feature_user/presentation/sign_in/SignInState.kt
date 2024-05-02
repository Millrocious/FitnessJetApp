package com.millrocious.fitness_jet_app.feature_user.presentation.sign_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)