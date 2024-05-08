package com.millrocious.fitness_jet_app.feature_user.domain.use_case

import com.millrocious.fitness_jet_app.feature_user.domain.model.UserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow

class GetUserInfo(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(): Flow<UserInfo> {
        return userInfoRepository.getUserProfileFlow()
    }
}