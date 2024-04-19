package com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case

data class LocationUseCases(
    val getCurrentLocation: GetCurrentLocation,
    val getCurrentRunState: GetCurrentRunState,
    val resumePauseTracking: StartResumePauseTracking,
    val stopTracking: StopTracking,
    val getAllRun: GetAllRun,
    val addCurrentRun: AddCurrentRun
)