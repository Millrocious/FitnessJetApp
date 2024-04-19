package com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.manager

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service.LocationService
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service.LocationServiceActions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultLocationServiceManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): LocationServiceManager {
    private val serviceIntent = Intent(applicationContext, LocationService::class.java)

    override fun startLocationService() {
        ContextCompat.startForegroundService(applicationContext, serviceIntent.apply {
            action = LocationServiceActions.ACTION_START.toString()
        })
    }
    override fun stopLocationService() {
        ContextCompat.startForegroundService(applicationContext, serviceIntent.apply {
            action = LocationServiceActions.ACTION_STOP.toString()
        })
    }

    override fun isLocationServiceRunning(): String {
        return LocationService.SERVICE_STATE.toString()
    }
}