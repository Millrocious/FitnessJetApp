package com.millrocious.fitness_jet_app.feature_map_tracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run): Long

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table WHERE id = :id")
    suspend fun getRunById(id: Int): Run?

    @Query("SELECT * FROM running_table")
    fun getAllRun(): Flow<List<Run>>
}