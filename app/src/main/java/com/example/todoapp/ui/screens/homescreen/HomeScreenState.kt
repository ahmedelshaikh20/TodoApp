package com.example.todoapp.ui.screens.homescreen

import com.example.domain.models.NoteModel

data class HomeScreenState(
  val note: String,
  var notes: List<NoteModel> = emptyList()
)
