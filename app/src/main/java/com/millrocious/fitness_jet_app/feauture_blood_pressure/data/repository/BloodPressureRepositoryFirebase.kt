package com.millrocious.fitness_jet_app.feauture_blood_pressure.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressureFirebase
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

class BloodPressureRepositoryFirebase(
    private val database: FirebaseDatabase
) : BloodPressureRepository {
    private val user = FirebaseAuth.getInstance().currentUser

    override suspend fun insertBloodPressure(bloodPressure: BloodPressure) {
        user?.let {
            database
                .getReference("users/${it.uid}/bloodPressures/${bloodPressure.uuid}")
                .setValue(
                    BloodPressureFirebase(
                        systolic = bloodPressure.systolic.toString(),
                        diastolic = bloodPressure.diastolic.toString(),
                        selectedTimestamp = bloodPressure.selectedTimestamp.toString(),
                        createdTimestamp = bloodPressure.createdTimestamp.toString(),
                        updatedTimestamp = bloodPressure.updatedTimestamp.toString(),
                    )
                )
        }

    }

    override suspend fun deleteBloodPressure(bloodPressure: BloodPressure) {
        user?.let {
            database
                .getReference("users/${it.uid}/bloodPressures/${bloodPressure.uuid}")
                .removeValue()
        }
    }

    override suspend fun getBloodPressureById(id: String): BloodPressure? {
        user?.let {
            database
                .getReference("users/${it.uid}/bloodPressures/$id")
                .get()
                .await()
                .getValue(BloodPressureFirebase::class.java)?.let { bloodPressure ->
                    return BloodPressure(
                        systolic = bloodPressure.systolic.toInt(),
                        diastolic = bloodPressure.diastolic.toInt(),
                        selectedTimestamp = OffsetDateTime.parse(bloodPressure.selectedTimestamp),
                        createdTimestamp = OffsetDateTime.parse(bloodPressure.createdTimestamp),
                        updatedTimestamp = OffsetDateTime.parse(bloodPressure.updatedTimestamp),
                        uuid = id
                    )
                }
        }

        return null
    }

    override fun getBloodPressures(): Flow<List<BloodPressure>> = callbackFlow {
        val userId = user?.uid ?: return@callbackFlow

        val heartRateRef = database.getReference("users/$userId/bloodPressures")

        val listener = heartRateRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bloodPressureList = mutableListOf<BloodPressure>()
                snapshot.children.forEach { child ->
                    val heartRateFirebase = child.getValue(BloodPressureFirebase::class.java)
                    heartRateFirebase?.let { bloodPressure ->
                        val heartRate = BloodPressure(
                            systolic = bloodPressure.systolic.toInt(),
                            diastolic = bloodPressure.diastolic.toInt(),
                            selectedTimestamp = OffsetDateTime.parse(bloodPressure.selectedTimestamp),
                            createdTimestamp = OffsetDateTime.parse(bloodPressure.createdTimestamp),
                            updatedTimestamp = OffsetDateTime.parse(bloodPressure.updatedTimestamp),
                            uuid = child.key ?: UUID.randomUUID().toString()
                        )
                        bloodPressureList.add(heartRate)
                    }
                }
                trySend(bloodPressureList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        awaitClose {
            heartRateRef.removeEventListener(listener)
        }
    }

    override fun getBloodPressuresByDate(): Flow<Map<LocalDate, List<BloodPressure>>> {
        return getBloodPressures().map { bloodPressures ->
            bloodPressures.groupBy { it.createdTimestamp.toLocalDate() }
                .toSortedMap(compareByDescending { it })
        }
    }

    override suspend fun updateBloodPressure(bloodPressure: BloodPressure) {
        user?.let {
            database
                .getReference("users/${it.uid}/bloodPressures/${bloodPressure.uuid}")
                .setValue(bloodPressure)
        }
    }
}