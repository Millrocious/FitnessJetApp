package com.millrocious.fitness_jet_app.feauture_blood_pressure.di

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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BloodPressureModule {
    @Provides
    @Singleton
    fun provideBloodPressureRepository(db: AppDatabase): BloodPressureRepository {
        return BloodPressureRepositoryImpl(db.bloodPressureDao)
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