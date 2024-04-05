package com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.millrocious.fitness_jet_app.R
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class LocationService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @Inject
    lateinit var locationRepository: LocationRepository

    override fun onCreate() {
        super.onCreate()
        IS_ACTIVITY_RUNNING = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = createNotification()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationRepository
            .getLocationUpdates()
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                updateNotification(notification, notificationManager, location)
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun createNotification(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
    }

    private fun updateNotification(
        notification: NotificationCompat.Builder,
        notificationManager: NotificationManager,
        location: Location
    ) {
        val lat = location.latitude.toString()
        val long = location.longitude.toString()

        val updatedNotification = notification.setContentText(
            "Location: ($lat, $long)"
        )

        notificationManager.notify(1, updatedNotification.build())
    }
    private fun stop() {
        stopForeground(STOP_FOREGROUND_DETACH)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        IS_ACTIVITY_RUNNING = false
        serviceScope.cancel()
    }

    companion object {
        var IS_ACTIVITY_RUNNING = false
            private set

        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}