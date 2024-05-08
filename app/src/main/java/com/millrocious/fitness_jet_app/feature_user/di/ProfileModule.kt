package com.millrocious.fitness_jet_app.feature_user.di

import com.google.firebase.database.FirebaseDatabase
import com.millrocious.fitness_jet_app.core.di.qualifiers.FireBaseDb
import com.millrocious.fitness_jet_app.feature_user.data.repository.UserInfoRepositoryFirebase
import com.millrocious.fitness_jet_app.feature_user.domain.repository.UserInfoRepository
import com.millrocious.fitness_jet_app.feature_user.domain.use_case.GetUserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.use_case.UpdateUserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.use_case.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    @FireBaseDb
    fun provideUserInfoRepositoryFirebase(fireBaseDb: FirebaseDatabase): UserInfoRepository {
        return UserInfoRepositoryFirebase(fireBaseDb)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(
        @FireBaseDb userInfoRepositoryFirebase: UserInfoRepository,
    ): UserUseCases {
        return UserUseCases(
            getUserInfo = GetUserInfo(userInfoRepositoryFirebase),
            updateUserInfo = UpdateUserInfo(userInfoRepositoryFirebase)
        )
    }
}