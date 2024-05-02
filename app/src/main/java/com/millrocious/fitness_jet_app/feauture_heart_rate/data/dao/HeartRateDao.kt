package com.millrocious.fitness_jet_app.feauture_heart_rate.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import kotlinx.coroutines.flow.Flow

@Dao
interface HeartRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeartRate(heartRate: HeartRate)

    @Delete
    suspend fun deleteHeartRate(heartRate: HeartRate)

    @Query("SELECT * FROM heartrate WHERE uuid = :id")
    suspend fun getHeartRateById(id: String): HeartRate?

    @Query("SELECT * FROM heartrate")
    fun getAllHeartRate(): Flow<List<HeartRate>>

    @Update
    suspend fun updateHeartRate(heartRate: HeartRate)
}
