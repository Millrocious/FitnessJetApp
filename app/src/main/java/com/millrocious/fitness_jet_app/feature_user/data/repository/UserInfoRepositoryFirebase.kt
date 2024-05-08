package com.millrocious.fitness_jet_app.feature_user.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.millrocious.fitness_jet_app.feature_user.domain.model.UserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

data class UserInfoRepositoryFirebase(
    private val database: FirebaseDatabase
) : UserInfoRepository {
    override suspend fun updateUserProfile(userInfo: UserInfo) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val userRef = database.reference.child("users").child(user.uid)

            userRef.updateChildren(
                mapOf(
                    "height" to userInfo.height,
                    "weight" to userInfo.weight,
                    "gender" to userInfo.gender,
                    "age" to userInfo.age,
                    "stepsGoal" to userInfo.stepsGoal
                )
            )
        }
    }

    override fun getUserProfileFlow(): Flow<UserInfo> = callbackFlow {
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val userRef = database.reference.child("users").child(user.uid)

            val listener = userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val profileData = snapshot.getValue(UserInfo::class.java)
                    trySend(profileData ?: UserInfo())
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })

            awaitClose { userRef.removeEventListener(listener) }
        }
    }
}