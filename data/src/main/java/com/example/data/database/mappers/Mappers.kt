package com.example.data.database.mappers

import com.example.data.database.model.NoteEntity
import com.example.domain.models.NoteModel


fun NoteModel.toNoteEntity(): NoteEntity {
  return NoteEntity(title, description, id)
}

fun NoteEntity.toNoteModel(): NoteModel {
  return NoteModel(title, description, id)
}
