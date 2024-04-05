package com.millrocious.fitness_jet_app.feature_map_tracker.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.millrocious.fitness_jet_app.feature_map_tracker.data.repository.LocationRepositoryImpl
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.client.LocationClient
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.location.manager.LocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.LocationRepository
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.GetCurrentLocation
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.use_case.LocationUseCases
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.manager.DefaultLocationServiceManager
import com.millrocious.fitness_jet_app.feature_map_tracker.framework.location.сlient.DefaultLocationClient
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
    fun provideLocationClient(@ApplicationContext context: Context, fusedLocationProviderClient: FusedLocationProviderClient): LocationClient {
        return DefaultLocationClient(context, fusedLocationProviderClient)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationClient: LocationClient): LocationRepository {
        return LocationRepositoryImpl(locationClient)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(repository: LocationRepository): LocationUseCases {
        return LocationUseCases(
            getCurrentLocation = GetCurrentLocation(repository),
        )
    }
}