package com.millrocious.fitness_jet_app.feature_map_tracker.framework.tracker

import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.CurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.model.PathPoint
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.LocationTrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.util.MetricHelper
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service.LocationService
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service.LocationServiceState
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToLong

@Singleton
class DefaultTrackingManager @Inject constructor(
    private val locationTrackingManager: LocationTrackingManager,
    private val timeTracker: TimeTracker,
    private val locationServiceManager: LocationServiceManager
): TrackingManager {
    private var isTracking = false
        set(value) {
            _currentRunState.update { it.copy(isTracking = value) }
            field = value
        }

    private val _currentRunState = MutableStateFlow(CurrentRunState())
    val currentRunState = _currentRunState

    private val _trackingDurationInMs = MutableStateFlow(0L)
    val trackingDurationInMs = _trackingDurationInMs.asStateFlow()

    private val timeTrackerCallback = { timeElapsed: Long ->
        _trackingDurationInMs.update { timeElapsed }
    }

    private var isFirst = true

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            if (isTracking) {
                result.locations.forEach { location ->
                    addPathPoints(location)
                }
            }
        }
    }

    private fun postInitialValue() {
        _currentRunState.update {
            CurrentRunState()
        }

        _trackingDurationInMs.update { 0 }
    }

    private fun addPathPoints(location: Location?) = location?.let {
        val pos = LatLng(it.latitude, it.longitude)

        _currentRunState.update { state ->
            val pathPoints = state.pathPoints + PathPoint.LocationPoint(pos, MetricHelper.getUserSpeedStateByValue(state.speedInKMH))
            state.copy(
                pathPoints = pathPoints,
                distanceInMeters = state.distanceInMeters.run {
                    var distance = this

                    if (pathPoints.size > 1) {
                        distance += RunUtils.getDistanceBetweenPathPoints(
                            pathPoint1 = pathPoints[pathPoints.size - 1],
                            pathPoint2 = pathPoints[pathPoints.size - 2]
                        )
                    }

                    distance
                },
                steps = (state.distanceInMeters / 0.8).roundToLong(),
                timeDuration = trackingDurationInMs.value,
                speedInKMH = (it.speed * 3.6f).toBigDecimal()
                    .setScale(2, RoundingMode.HALF_UP).toFloat()
            )
        }
    }

    override fun startResumeTracking() {
        if (isTracking) return
        if (isFirst) {
            postInitialValue()
            locationServiceManager.startLocationService()
            isFirst = false
        }
        isTracking = true
        timeTracker.startResumeTimer(timeTrackerCallback)
        locationTrackingManager.registerCallback(locationCallback)
        LocationService.SERVICE_STATE = LocationServiceState.RUNNING
    }

//    private fun addEmptyPolyLine() {
//        _currentRunState.update {
//            it.copy(
//                pathPoints = it.pathPoints + PathPoint.EmptyLocationPoint
//            )
//        }
//    }

    override fun pauseTracking() {
        isTracking = false
        locationTrackingManager.unRegisterCallback(locationCallback)
        timeTracker.pauseTimer()
        LocationService.SERVICE_STATE = LocationServiceState.PAUSED
        //addEmptyPolyLine()
    }

    override fun stop() {
        pauseTracking()
        locationServiceManager.stopLocationService()
        timeTracker.stopTimer()
        postInitialValue()
        isFirst = true
    }

    override fun getCurrentRunStateFlow(): Flow<CurrentRunState> {
        return currentRunState
    }

    override fun getTrackingDurationInMsFlow(): Flow<Long> {
        return trackingDurationInMs
    }
}