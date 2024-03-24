package com.millrocious.fitness_jet_app.di

import android.app.Application
import androidx.room.Room
import com.millrocious.fitness_jet_app.core.data.data_source.AppDatabase
import com.millrocious.fitness_jet_app.feauture_blood_pressure.data.repository.BloodPressureRepositoryImpl
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.AddBloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.BloodPressureUseCases
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.CalculateBloodPressureStatistics
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.DeleteBloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.GetBloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.GetBloodPressures
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.GetBloodPressuresGroupedByDate
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java, "HS_App_db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideHealthStatsInfoRepository(db: AppDatabase): HealthStatsInfoRepository {
//        return HealthStatsInfoRepositoryImpl(db.healthStatsInfoDao)
//    }

    @Provides
    @Singleton
    fun provideHeartRateRepository(db: AppDatabase): HeartRateRepository {
        return HeartRateRepositoryImpl(db.heartRateDao)
    }

    @Provides
    @Singleton
    fun provideBloodPressureRepository(db: AppDatabase): BloodPressureRepository {
        return BloodPressureRepositoryImpl(db.bloodPressureDao)
    }

//    @Provides
//    @Singleton
//    fun provideUserInfoRepository(db: AppDatabase): UserInfoRepository {
//        return UserInfoRepositoryImpl(db.userInfoDao)
//    }

    @Provides
    @Singleton
    fun provideHeartRateUseCases(repository: HeartRateRepository): HeartRateUseCases {
        return HeartRateUseCases(
            addHeartRate = AddHeartRate(repository),
            deleteHeartRate = DeleteHeartRate(repository),
            getAllHeartRate = GetAllHeartRate(repository),
            getHeartRate = GetHeartRate(repository),
            getHeartRatesGroupedByDate = GetHeartRatesGroupedByDate(repository),
            calculateHeartRateStatisticsForToday = CalculateHeartRateStatisticsForToday(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBloodPressureUseCases(repository: BloodPressureRepository): BloodPressureUseCases {
        return BloodPressureUseCases(
            addBloodPressure = AddBloodPressure(repository),
            deleteBloodPressure = DeleteBloodPressure(repository),
            getBloodPressures = GetBloodPressures(repository),
            getBloodPressure = GetBloodPressure(repository),
            getBloodPressuresGroupedByDate = GetBloodPressuresGroupedByDate(repository),
            calculateBloodPressureStatistics = CalculateBloodPressureStatistics(repository)
        )
    }
}