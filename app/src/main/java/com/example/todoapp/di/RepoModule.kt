package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.data.api.notes.NotesClient
import com.example.data.api.user.UserApiClient
import com.example.data.database.NotesDatabase
import com.example.data.database.dao.NotesDao
import com.example.data.repo.NotesRepositoryImpl
import com.example.data.repo.UserRepositoryImpl
import com.example.domain.repositories.NotesRepository
import com.example.domain.repositories.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


  @Provides
  fun provideRepo(userApiClient: UserApiClient): UserRepo {
    return UserRepositoryImpl(userApiClient)
  }

  @Provides
  fun provideNotesRepo(notesClient: NotesClient): NotesRepository {
    return NotesRepositoryImpl(notesClient)
  }

  @Provides
  @Singleton
  fun provideNotesClient(notesDatabase: NotesDatabase): NotesClient {
    return NotesClient(notesDatabase.dao)
  }

  @Provides
  @Singleton
  fun provideDatabase(application: Application): NotesDatabase {
    return Room.databaseBuilder(
      application,
      NotesDatabase::class.java,
      "notesdatabase"
    ).build()
  }

}
