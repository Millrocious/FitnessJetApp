package com.millrocious.fitness_jet_app.core.di

import android.app.Application
import androidx.room.Room
import com.millrocious.fitness_jet_app.core.data.data_source.AppDatabase
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
}