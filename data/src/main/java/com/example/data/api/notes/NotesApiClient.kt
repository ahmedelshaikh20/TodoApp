package com.example.data.api.notes

import android.util.Log
import com.example.data.database.NotesDatabase
import com.example.data.database.dao.NotesDao
import com.example.data.database.mappers.toNoteEntity
import com.example.data.database.mappers.toNoteModel
import com.example.domain.models.NoteModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class NotesClient @Inject constructor(
  val notesDao: NotesDao
) {

  suspend fun addNote(note: NoteModel) {
    val noteEntity = note.toNoteEntity()
    notesDao.insertNote(noteEntity)
  }

  suspend fun getAllNotes(): List<NoteModel> {
    val notesEntities = notesDao.getAllNotes()
    val notesModel = notesEntities.map {
      it.toNoteModel()
    }
    return notesModel
  }
}
