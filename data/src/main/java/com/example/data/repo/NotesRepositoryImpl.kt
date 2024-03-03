package com.example.data.repo

import com.example.data.api.notes.NotesClient
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor (private val notesClient: NotesClient) : NotesRepository{
  override suspend fun addNote(note: NoteModel) {
    notesClient.addNote(note)
  }

  override suspend fun getAllNotes():List<NoteModel> {
   return notesClient.getAllNotes()
  }
}
