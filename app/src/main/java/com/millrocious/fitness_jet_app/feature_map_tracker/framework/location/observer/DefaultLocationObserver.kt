package com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.observer

import android.location.Location
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.observer.LocationObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class DefaultLocationObserver : LocationObserver {
    private val _locationFlow = MutableSharedFlow<Location>()
    override val locationFlow: SharedFlow<Location> = _locationFlow

    override fun emitLocation(location: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            _locationFlow.emit(location)
        }
    }
}