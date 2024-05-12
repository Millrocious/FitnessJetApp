package com.millrocious.fitness_jet_app.feature_map_tracker.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.millrocious.fitness_jet_app.core.di.qualifiers.FireBaseDb
import com.millrocious.fitness_jet_app.feature_map_tracker.data.repository.LocationRepositoryImpl
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.client.LocationClient
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.tracker.TrackingManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.AddCurrentRun
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetAllRun
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetCurrentLocation
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetCurrentRunState
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetCurrentRunStateWithCalories
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetRunById
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.LocationUseCases
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.StartResumePauseTracking
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.StopTracking
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.manager.DefaultLocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.сlient.DefaultLocationClient
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideLocationServiceManager(@ApplicationContext context: Context): LocationServiceManager {
        return DefaultLocationServiceManager(context)
    }

    @Provides
    @Singleton
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationClient(
        @ApplicationContext context: Context,
        fusedLocationProviderClient: FusedLocationProviderClient
    ): LocationClient {
        return DefaultLocationClient(context, fusedLocationProviderClient)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationClient: LocationClient): LocationRepository {
        return LocationRepositoryImpl(locationClient)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(
        @FireBaseDb runRepository: RunRepository,
        repository: LocationRepository,
        trackingManager: TrackingManager,
        @FireBaseDb userRepository: UserInfoRepository,
    ): LocationUseCases {
        return LocationUseCases(
            getCurrentLocation = GetCurrentLocation(repository),
            getCurrentRunState = GetCurrentRunState(trackingManager),
            resumePauseTracking = StartResumePauseTracking(trackingManager),
            stopTracking = StopTracking(trackingManager),
            getAllRun = GetAllRun(runRepository),
            addCurrentRun = AddCurrentRun(runRepository),
            getRunById = GetRunById(runRepository),
            getCurrentRunStateWithCalories = GetCurrentRunStateWithCalories(trackingManager, userRepository)
        )
    }
}