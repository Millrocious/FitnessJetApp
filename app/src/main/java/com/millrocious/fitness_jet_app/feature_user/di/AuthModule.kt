package com.millrocious.fitness_jet_app.feature_user.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.millrocious.fitness_jet_app.feature_user.framework.google_client.GoogleAuthUiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(@ApplicationContext context: Context): GoogleAuthUiClient {
        return GoogleAuthUiClient(
            context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
}