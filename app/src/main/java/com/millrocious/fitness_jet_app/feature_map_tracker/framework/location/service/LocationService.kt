package com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.notification.NotificationHelper
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class LocationService : LifecycleService() {
    @Inject
    lateinit var trackingManager: TrackingManager

    @Inject
    lateinit var notificationHelper: NotificationHelper

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        SERVICE_STATE = LocationServiceState.RUNNING
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            LocationServiceActions.ACTION_START.toString() -> start()
            LocationServiceActions.ACTION_RESUME.toString() -> trackingManager.startResumeTracking()
            LocationServiceActions.ACTION_PAUSE.toString() -> trackingManager.pauseTracking()
            LocationServiceActions.ACTION_STOP.toString() -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        startForeground(
            NotificationHelper.TRACKING_NOTIFICATION_ID,
            notificationHelper.baseNotificationBuilder.build()
        )

//        trackingManager.getTrackingDurationInMsFlow().onEach { duration ->
//            notificationHelper.updateTrackingNotification(
//                durationInMillis = duration
//            )
//        }.launchIn(serviceScope)

        if (job == null) {
            job = trackingManager.getTrackingDurationInMsFlow().onEach { duration ->
                notificationHelper.updateTrackingNotification(
                    durationInMillis = duration
                )
            }.launchIn(lifecycleScope)
        }
    }

    private fun stop() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        job = null

        SERVICE_STATE = LocationServiceState.NOT_ACTIVE
    }

    companion object {
        var SERVICE_STATE = LocationServiceState.NOT_ACTIVE
    }
}