package com.millrocious.fitness_jet_app.feature_map_tracker.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.RunFirebase
import com.millrocious.fitness_jet_app.feature_map_tracker.domain.repository.RunRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class RunRepositoryFirebase @Inject constructor(
    private val database: FirebaseDatabase
) : RunRepository {
    override suspend fun insertRun(run: Run): String {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val runRef = database.getReference("users/${it.uid}/runs/${run.uuid}")
            runRef.keepSynced(true)

            runRef
                .setValue(
                    RunFirebase(
                        img = bitmapToString(run.img),
                        steps = run.steps.toString(),
                        avgSpeedInKMH = run.avgSpeedInKMH.toString(),
                        distanceInMeters = run.distanceInMeters.toString(),
                        durationInMillis = run.durationInMillis.toString(),
                        caloriesBurned = run.caloriesBurned.toString(),
                        timestamp = run.timestamp.toString()
                    )
                )

            return run.uuid
        }

        return run.uuid
    }

    override suspend fun deleteRun(run: Run) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val runRef = database.getReference("users/${it.uid}/runs/${run.uuid}")
            runRef.keepSynced(true)

            runRef.removeValue()
        }
    }

    override suspend fun getRunById(id: String): Run? {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val heartRateRef = database.getReference("users/${it.uid}/runs/$id")
            heartRateRef.keepSynced(true)

            val heartRateFirebase = heartRateRef.get().await().getValue(RunFirebase::class.java)

            heartRateFirebase?.let { run ->
                val runValues = listOf(
                    run.steps,
                    run.avgSpeedInKMH,
                    run.distanceInMeters,
                    run.durationInMillis,
                    run.caloriesBurned,
                    run.timestamp
                )

                if (runValues.none { value -> value.isEmpty() }) {
                    val img = stringToBitmap(run.img)?.let { img ->
                        return Run(
                            img = img,
                            steps = run.steps.toLong(),
                            avgSpeedInKMH = run.avgSpeedInKMH.toFloat(),
                            distanceInMeters = run.distanceInMeters.toInt(),
                            durationInMillis = run.durationInMillis.toLong(),
                            caloriesBurned = run.caloriesBurned.toInt(),
                            timestamp = OffsetDateTime.parse(run.timestamp)
                        )
                    }
                }
            }
        }

        return null
    }

    override fun getAllRun(): Flow<List<Run>> = callbackFlow {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@callbackFlow

        val runRef = database.getReference("users/$userId/runs")
        runRef.keepSynced(true)

        val listener = runRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val runList = mutableListOf<Run>()
                snapshot.children.forEach { child ->
                    val runFirebase = child.getValue(RunFirebase::class.java)
                    runFirebase?.let { run ->
                        val runValues = listOf(
                            run.img,
                            run.steps,
                            run.avgSpeedInKMH,
                            run.distanceInMeters,
                            run.durationInMillis,
                            run.caloriesBurned,
                            run.timestamp
                        )

                        if (runValues.none { value -> value.isEmpty() }) {
                            runList.add(
                                Run(
                                    img = stringToBitmap(run.img)!!,
                                    steps = run.steps.toLong(),
                                    avgSpeedInKMH = run.avgSpeedInKMH.toFloat(),
                                    distanceInMeters = run.distanceInMeters.toInt(),
                                    durationInMillis = run.durationInMillis.toLong(),
                                    caloriesBurned = run.caloriesBurned.toInt(),
                                    timestamp = OffsetDateTime.parse(run.timestamp)
                                )
                            )
                        }
                    }
                }
                trySend(runList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        awaitClose {
            runRef.removeEventListener(listener)
        }
    }

    override fun getTotalStepsByToday(): Flow<Long?> = callbackFlow {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@callbackFlow

        val today = LocalDate.now()
        val todayStart = today.atStartOfDay().toInstant(ZoneOffset.UTC)
        val todayEnd = today.atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC)

        val runRef = database.getReference("users/$userId/runs")
        val listener = runRef.orderByChild("timestamp")
            .startAt(todayStart.toString())
            .endAt(todayEnd.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var totalSteps = 0L
                    snapshot.children.forEach { child ->
                        val runFirebase = child.getValue(RunFirebase::class.java)
                        runFirebase?.let { run ->
                            totalSteps += run.steps.toLong()
                        }
                    }
                    trySend(totalSteps)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle cancellation
                }
            })

        awaitClose {
            runRef.removeEventListener(listener)
        }
    }

    override fun getTotalBurnedCaloriesByToday(): Flow<Int?> = callbackFlow {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@callbackFlow

        val today = LocalDate.now()
        val todayStart = today.atStartOfDay().toInstant(ZoneOffset.UTC)
        val todayEnd = today.atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC)

        val runRef = database.getReference("users/$userId/runs")
        val listener = runRef.orderByChild("timestamp")
            .startAt(todayStart.toString())
            .endAt(todayEnd.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var totalBurnedCalories = 0
                    snapshot.children.forEach { child ->
                        val runFirebase = child.getValue(RunFirebase::class.java)
                        runFirebase?.let { run ->
                            if (run.caloriesBurned != "") {
                                totalBurnedCalories += run.caloriesBurned.toInt()
                            }
                        }
                    }
                    trySend(totalBurnedCalories)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle cancellation
                }
            })

        awaitClose {
            runRef.removeEventListener(listener)
        }
    }
}

fun bitmapToString(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun stringToBitmap(encodedString: String): Bitmap? {
    try {
        val decodedByteArray: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}
