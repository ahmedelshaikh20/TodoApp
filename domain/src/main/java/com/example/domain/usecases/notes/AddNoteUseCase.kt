package com.example.domain.usecases.notes

import com.example.domain.models.NoteModel
import com.example.domain.repositories.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(val notesRepository: NotesRepository) {
  suspend operator fun invoke(note:NoteModel)  = notesRepository.addNote(note)
}
