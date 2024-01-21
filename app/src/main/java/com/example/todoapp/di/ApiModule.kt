package com.example.todoapp.di

import com.example.data.api.user.UserApiClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  fun provideUserApiClient(firebaseAuth: FirebaseAuth): UserApiClient {
    return UserApiClient(firebaseAuth)
  }
}
