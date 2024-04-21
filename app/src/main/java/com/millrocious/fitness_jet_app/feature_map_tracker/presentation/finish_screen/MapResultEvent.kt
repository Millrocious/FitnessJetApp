package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen

import android.graphics.Bitmap

sealed class MapResultEvent {
//    object StartLocationService : MapEvent()
    object StopLocationService : MapResultEvent()
//    data class AddPathPoint(val latLng: LatLng) : MapEvent()
//    object ClearPathPoints : MapEvent()
    object ResumePauseTracking : MapResultEvent()
    data class FinishRun(val bitmap: Bitmap) : MapResultEvent()
}