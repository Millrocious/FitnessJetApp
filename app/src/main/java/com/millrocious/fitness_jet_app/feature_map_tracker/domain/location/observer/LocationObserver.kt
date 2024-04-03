package com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.observer

import android.location.Location
import kotlinx.coroutines.flow.SharedFlow

// Interface for the GeoLocationBroker
interface LocationObserver {
    val locationFlow: SharedFlow<Location>
    fun emitLocation(location: Location)
}