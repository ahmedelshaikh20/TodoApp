package com.example.data.repo

import com.example.data.api.notes.NotesApiClient
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor (private val notesApiClient: NotesApiClient) : NotesRepository{
  override suspend fun addNote(note: NoteModel) {
    notesApiClient.addNote(note)
  }
}
