package com.example.todoapp.di

import com.example.data.api.user.UserApiClient
import com.example.data.repo.UserRepoImpl
import com.example.domain.repositories.UserRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule{


  @Provides
  fun provideRepo (userApiClient: UserApiClient) : UserRepo{
    return UserRepoImpl(userApiClient)
  }
  fun provideUserApiClient (firebaseAuth: FirebaseAuth) : UserApiClient{
    return UserApiClient(firebaseAuth)
  }
}
