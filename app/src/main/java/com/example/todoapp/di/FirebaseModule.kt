package com.example.todoapp.di

import com.example.data.api.user.UserApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

  @Provides
  fun provideFireAuth(): FirebaseAuth {
    return Firebase.auth

  }

}
