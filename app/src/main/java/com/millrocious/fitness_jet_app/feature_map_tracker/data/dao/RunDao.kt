package com.millrocious.fitness_jet_app.feature_map_tracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
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

    @Query("SELECT SUM(steps) FROM running_table WHERE date(timestamp) = date('now')")
    fun getTotalStepsByToday(): Flow<Long?>
}