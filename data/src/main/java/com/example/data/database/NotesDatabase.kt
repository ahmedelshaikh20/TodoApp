package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.NotesDao
import com.example.data.database.model.NoteEntity

@Database(entities =  [NoteEntity::class] , version = 1)
abstract class NotesDatabase : RoomDatabase() {
  abstract val dao : NotesDao
}
