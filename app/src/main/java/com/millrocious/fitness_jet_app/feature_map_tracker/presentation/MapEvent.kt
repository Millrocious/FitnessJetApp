package com.millrocious.fitness_jet_app.feature_map_tracker.presentation

sealed class MapEvent {
    object StartLocationService : MapEvent()
    object StopLocationService : MapEvent()
}