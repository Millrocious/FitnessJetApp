package com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager


interface LocationServiceManager {
    fun startLocationService()
    fun stopLocationService()
    fun isLocationServiceRunning(): String
}