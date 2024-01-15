package com.example.todoapp.di

import com.example.data.repo.RemoteRepoImpl
import com.example.domain.repositories.RemoteRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule{


  @Provides
  fun provideRepo (firebaseAuth: FirebaseAuth) : RemoteRepo{
    return RemoteRepoImpl(firebaseAuth)
  }

}
