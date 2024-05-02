package com.millrocious.fitness_jet_app.feauture_heart_rate.di

import com.google.firebase.database.FirebaseDatabase
import com.millrocious.fitness_jet_app.core.data.data_source.AppDatabase
import com.millrocious.fitness_jet_app.feauture_heart_rate.data.repository.HeartRateRepositoryFirebase
import com.millrocious.fitness_jet_app.feauture_heart_rate.data.repository.HeartRateRepositoryImpl
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.AddHeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.CalculateHeartRateStatisticsForToday
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.DeleteHeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.GetAllHeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.GetHeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.GetHeartRatesGroupedByDate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.HeartRateUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeartRateModule {

    @Provides
    @Singleton
    @RoomDb
    fun provideHeartRateRepository(db: AppDatabase): HeartRateRepository {
        return HeartRateRepositoryImpl(db.heartRateDao)
    }

    @Provides
    @Singleton
    @FireBaseDb
    fun provideHeartRateRepositoryFirebase(db: FirebaseDatabase): HeartRateRepository {
        return HeartRateRepositoryFirebase(db)
    }


    @Provides
    @Singleton
    fun provideHeartRateUseCases(@FireBaseDb repository: HeartRateRepository): HeartRateUseCases {
        return HeartRateUseCases(
            addHeartRate = AddHeartRate(repository),
            deleteHeartRate = DeleteHeartRate(repository),
            getAllHeartRate = GetAllHeartRate(repository),
            getHeartRate = GetHeartRate(repository),
            getHeartRatesGroupedByDate = GetHeartRatesGroupedByDate(repository),
            calculateHeartRateStatisticsForToday = CalculateHeartRateStatisticsForToday(repository)
        )
    }
}

@Qualifier
annotation class FireBaseDb

@Qualifier
annotation class RoomDb