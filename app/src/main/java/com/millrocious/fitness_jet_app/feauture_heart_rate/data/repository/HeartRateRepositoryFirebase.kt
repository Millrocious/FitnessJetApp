package com.millrocious.fitness_jet_app.feauture_heart_rate.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRateFirebase
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.repository.HeartRateRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

class HeartRateRepositoryFirebase(
    private val database: FirebaseDatabase
) : HeartRateRepository {
    override suspend fun insertHeartRate(heartRate: HeartRate) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            database
                .getReference("users/${it.uid}/heartRates/${heartRate.uuid}")
                .setValue(
                    HeartRateFirebase(
                        heartBeats = heartRate.heartBeats.toString(),
                        selectedTimestamp = heartRate.selectedTimestamp.toString(),
                        createdTimestamp = heartRate.createdTimestamp.toString(),
                        updatedTimestamp = heartRate.updatedTimestamp.toString(),
                    )
                )
        }

    }

    override suspend fun deleteHeartRate(heartRate: HeartRate) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            database
                .getReference("users/${it.uid}/heartRates/${heartRate.uuid}")
                .removeValue()
        }
    }

    override suspend fun getHeartRateById(id: String): HeartRate? {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val heartRateRef = database.getReference("users/${it.uid}/heartRates/$id")

            val heartRateFirebase = heartRateRef.get().await().getValue(HeartRateFirebase::class.java)

            heartRateFirebase?.let {
                return HeartRate(
                    heartBeats = heartRateFirebase.heartBeats.toInt(),
                    selectedTimestamp = OffsetDateTime.parse(heartRateFirebase.selectedTimestamp),
                    createdTimestamp = OffsetDateTime.parse(heartRateFirebase.createdTimestamp),
                    updatedTimestamp = OffsetDateTime.parse(heartRateFirebase.updatedTimestamp),
                    uuid = id
                )
            }
        }

        return null
    }

    override fun getAllHeartRate(): Flow<List<HeartRate>> = callbackFlow {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@callbackFlow

        val heartRateRef = database.getReference("users/$userId/heartRates")
        val listener = heartRateRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val heartRateList = mutableListOf<HeartRate>()
                snapshot.children.forEach { child ->
                    val heartRateFirebase = child.getValue(HeartRateFirebase::class.java)
                    heartRateFirebase?.let {
                        val heartRate = HeartRate(
                            heartBeats = heartRateFirebase.heartBeats.toInt(),
                            selectedTimestamp = OffsetDateTime.parse(heartRateFirebase.selectedTimestamp),
                            createdTimestamp = OffsetDateTime.parse(heartRateFirebase.createdTimestamp),
                            updatedTimestamp = OffsetDateTime.parse(heartRateFirebase.updatedTimestamp),
                            uuid = child.key ?: UUID.randomUUID().toString()
                        )
                        heartRateList.add(heartRate)
                    }
                }
                trySend(heartRateList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        awaitClose {
            heartRateRef.removeEventListener(listener)
        }
    }

    override fun getHeartRateGroupedByDate(): Flow<Map<LocalDate, List<HeartRate>>> {
        return getAllHeartRate().map { heartRates ->
            heartRates.groupBy { it.createdTimestamp.toLocalDate() }
                .toSortedMap(compareByDescending { it })
        }
    }

    override suspend fun updateHeartRate(heartRate: HeartRate) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            database
                .getReference("users/${it.uid}/heartRates/${heartRate.uuid}")
                .setValue(heartRate)
        }
    }
}