package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.NoteEntity


@Dao
interface NotesDao {
  @Insert
  suspend fun insertNote(noteEntity: NoteEntity)
  @Delete
  suspend fun deleteNote(noteEntity: NoteEntity)
  @Query("Select * from NoteEntity")
  suspend fun getAllNotes(): List<NoteEntity>
}
