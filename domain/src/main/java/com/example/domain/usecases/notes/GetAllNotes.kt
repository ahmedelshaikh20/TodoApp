package com.example.domain.usecases.notes

import com.example.domain.models.NoteModel
import com.example.domain.repositories.NotesRepository
import javax.inject.Inject

class GetAllNotes @Inject constructor( val notesRepository: NotesRepository) {
  suspend operator fun invoke(): List<NoteModel> {
   return notesRepository.getAllNotes()
  }


}
