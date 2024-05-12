package com.millrocious.fitness_jet_app.feature_user.domain.use_case

import com.millrocious.fitness_jet_app.feature_user.domain.model.UserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository

class UpdateUserInfo(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(userInfo: UserInfo) {
        userInfoRepository.updateUserProfile(userInfo)
    }
}