package com.millrocious.fitness_jet_app.feauture_blood_pressure.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBloodPressure(bloodPressure: BloodPressure)

    @Delete
    suspend fun deleteBloodPressure(bloodPressure: BloodPressure)

    @Query("SELECT * FROM bloodpressure WHERE id = :id")
    suspend fun getBloodPressureById(id: Int): BloodPressure?

    @Query("SELECT * FROM bloodpressure")
    fun getBloodPressures(): Flow<List<BloodPressure>>

    @Update
    suspend fun updateBloodPressure(bloodPressure: BloodPressure)
}
