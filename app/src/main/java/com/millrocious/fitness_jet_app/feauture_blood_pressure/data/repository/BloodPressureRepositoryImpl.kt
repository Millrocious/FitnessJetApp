package com.millrocious.fitness_jet_app.feauture_blood_pressure.data.repository

import com.millrocious.fitness_jet_app.feauture_blood_pressure.data.dao.BloodPressureDao
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class BloodPressureRepositoryImpl(
    private val dao: BloodPressureDao
): BloodPressureRepository {
    override suspend fun insertBloodPressure(bloodPressure: BloodPressure) {
        dao.insertBloodPressure(bloodPressure)
    }

    override suspend fun deleteBloodPressure(bloodPressure: BloodPressure) {
        dao.deleteBloodPressure(bloodPressure)
    }

    override suspend fun getBloodPressureById(id: Int): BloodPressure? {
        return dao.getBloodPressureById(id)
    }

    override fun getBloodPressures(): Flow<List<BloodPressure>> {
        return dao.getBloodPressures()
    }

    override fun getBloodPressuresByDate(): Flow<Map<LocalDate, List<BloodPressure>>> {
        return dao.getBloodPressures()
            .map { bloodPressures ->
                bloodPressures.groupBy { it.createdTimestamp.toLocalDate() }
                    .toSortedMap(compareByDescending { it })
            }
    }

    override suspend fun updateBloodPressure(bloodPressure: BloodPressure) {
        dao.updateBloodPressure(bloodPressure)
    }
}