package com.millrocious.fitness_jet_app.feature_map_tracker.framework.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.millrocious.fitness_jet_app.R
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.notification.NotificationHelper
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.notification.NotificationHelper.Companion.TRACKING_NOTIFICATION_ID
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationHelper {

    companion object {
        private const val TRACKING_NOTIFICATION_CHANNEL_ID = "tracking_notification"
        private const val TRACKING_NOTIFICATION_CHANNEL_NAME = "Run Tracking Status"
    }

    override val baseNotificationBuilder
        get() = NotificationCompat.Builder(
            context,
            TRACKING_NOTIFICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentTitle("Running Time")
            .setContentText("00:00:00")


    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun updateTrackingNotification(durationInMillis: Long) {
        val notification = baseNotificationBuilder
            .setContentText(RunUtils.getFormattedStopwatchTime(durationInMillis))


        notificationManager.notify(TRACKING_NOTIFICATION_ID, notification.build())
    }

    override fun removeTrackingNotification() {
        notificationManager.cancel(TRACKING_NOTIFICATION_ID)
    }

    override fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        val notificationChannel = NotificationChannel(
            TRACKING_NOTIFICATION_CHANNEL_ID,
            TRACKING_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(notificationChannel)
    }
}