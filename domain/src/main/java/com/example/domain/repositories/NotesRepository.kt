package com.example.domain.repositories

import com.example.domain.models.NoteModel

interface NotesRepository {
  suspend fun addNote(note: NoteModel)
  suspend fun getAllNotes(): List<NoteModel>

}
