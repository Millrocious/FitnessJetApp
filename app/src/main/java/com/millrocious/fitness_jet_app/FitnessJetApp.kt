package com.millrocious.fitness_jet_app

import android.app.Application
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FitnessJetApp: Application() {
    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        notificationHelper.createNotificationChannel()
    }
}