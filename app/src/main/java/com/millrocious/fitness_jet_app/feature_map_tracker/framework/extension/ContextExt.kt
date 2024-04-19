package com.millrocious.fitness_jet_app.feature_map_tracker.framework.extension

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.util.RunUtils

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PERMISSION_GRANTED
}

fun Context.hasLocationPermission2() =
    mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    ).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            add(Manifest.permission.FOREGROUND_SERVICE_LOCATION)
        }
    }.toTypedArray().all {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PERMISSION_GRANTED
    }

fun Context.hasNotificationPermission() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            this,
            RunUtils.notificationPermission
        ) == PERMISSION_GRANTED
    } else true

fun Context.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}