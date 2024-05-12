package com.millrocious.fitness_jet_app.feature_user.domain.repository

import com.millrocious.fitness_jet_app.feature_user.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    suspend fun updateUserProfile(userInfo: UserInfo)
    fun getUserProfileFlow(): Flow<UserInfo>
}
