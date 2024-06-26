package com.millrocious.fitness_jet_app.feature_home.di

import com.millrocious.fitness_jet_app.core.di.qualifiers.FireBaseDb
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.GetAllBurnedCaloriesByToday
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.GetAllRun
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.GetAllStepsByToday
import com.millrocious.fitness_jet_app.feature_home.domain.use_case.HomeUseCases
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeUseCases(
        @FireBaseDb runRepository: RunRepository,
    ): HomeUseCases {
        return HomeUseCases(
            getAllRun = GetAllRun(runRepository),
            getAllStepsByToday = GetAllStepsByToday(runRepository),
            getAllBurnedCaloriesByToday = GetAllBurnedCaloriesByToday(runRepository)
        )
    }
}