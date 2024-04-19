package com.millrocious.fitness_jet_app.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.millrocious.fitness_jet_app.core.data.type_converter.BitmapTypeConverter
import com.millrocious.fitness_jet_app.feauture_heart_rate.data.dao.HeartRateDao
import com.millrocious.fitness_jet_app.core.data.type_converter.TimestampTypeConverter
import com.millrocious.fitness_jet_app.feature_map_tracker.data.dao.RunDao
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feauture_blood_pressure.data.dao.BloodPressureDao
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate

@Database(
    entities = [
        HeartRate::class,
        BloodPressure::class,
        Run::class
    ],
    version = 1,
)
@TypeConverters(TimestampTypeConverter::class, BitmapTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val heartRateDao: HeartRateDao
    abstract val bloodPressureDao: BloodPressureDao
    abstract val runDao: RunDao
}