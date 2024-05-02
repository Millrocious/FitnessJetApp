package com.millrocious.fitness_jet_app.feature_map_tracker.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.database.FirebaseDatabase
import com.millrocious.fitness_jet_app.core.data.data_source.AppDatabase
import com.millrocious.fitness_jet_app.core.di.qualifiers.FireBaseDb
import com.millrocious.fitness_jet_app.core.di.qualifiers.RoomDb
import com.millrocious.fitness_jet_app.feature_map_tracker.data.repository.RunRepositoryFirebase
import com.millrocious.fitness_jet_app.feature_map_tracker.data.repository.RunRepositoryImpl
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.notification.NotificationHelper
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.LocationTrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.tracker.DefaultLocationTrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.config.LocationConfig
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.notification.DefaultNotificationHelper
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.tracker.TimeTracker
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.tracker.DefaultTrackingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackingModule {

    @Provides
    @Singleton
    @RoomDb
    fun provideRunRepository(db: AppDatabase): RunRepository {
        return RunRepositoryImpl(db.runDao)
    }

    @Provides
    @Singleton
    @FireBaseDb
    fun provideRunRepositoryFirebase(db: FirebaseDatabase): RunRepository {
        return RunRepositoryFirebase(db)
    }

    @Singleton
    @Provides
    fun provideLocationTrackingManager(
        @ApplicationContext context: Context,
        fusedLocationProviderClient: FusedLocationProviderClient,
    ): LocationTrackingManager {
        return DefaultLocationTrackingManager(
            fusedLocationProviderClient = fusedLocationProviderClient,
            context = context,
            locationRequest = LocationConfig.locationRequestBuilder.build()
        )
    }

    @Provides
    @Singleton
    fun provideNotificationHelper(
        @ApplicationContext context: Context
    ): NotificationHelper {
        return DefaultNotificationHelper(context)
    }


    @Provides
    @Singleton
    fun provideTrackingManager(
        locationTrackingManager: LocationTrackingManager,
        timeTracker: TimeTracker,
        locationServiceManager: LocationServiceManager
    ): TrackingManager {
        return DefaultTrackingManager(locationTrackingManager, timeTracker, locationServiceManager)
    }
}