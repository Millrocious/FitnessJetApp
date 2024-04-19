package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.map_screen

import android.graphics.Bitmap

sealed class MapEvent {
//    object StartLocationService : MapEvent()
    object StopLocationService : MapEvent()
//    data class AddPathPoint(val latLng: LatLng) : MapEvent()
//    object ClearPathPoints : MapEvent()
    object ResumePauseTracking : MapEvent()
    data class FinishRun(val bitmap: Bitmap) : MapEvent()
}