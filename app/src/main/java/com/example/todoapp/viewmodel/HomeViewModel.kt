package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.NoteModel
import com.example.domain.models.UserInfoModel
import com.example.domain.usecases.notes.AddNoteUseCase
import com.example.domain.usecases.notes.GetAllNotes
import com.example.domain.usecases.user.GetCurrentUserUseCase
import com.example.todoapp.ui.screens.homescreen.HomeScreenState
import com.example.todoapp.ui.screens.homescreen.HomeScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val getAllNotesFromRepo: GetAllNotes,
  val addNote: AddNoteUseCase
) :
  ViewModel() {


  var state by mutableStateOf(HomeScreenState(""))
  fun addNote() {
    viewModelScope.launch {
      try {
        val noteModel = NoteModel(state.note, "")
        addNote(noteModel)
      } catch (e: Exception) {
        Log.d("Error", e.message.toString())
      }
    }
  }

  fun getAllNotes() {
    viewModelScope.launch {
      try {
        state.notes = getAllNotesFromRepo()
      } catch (e: Exception) {
        Log.d("Error", e.message.toString())
      }
    }
  }

  fun onEvent(homeScreenUiEvent: HomeScreenUiEvent) {
    when (homeScreenUiEvent) {
      is HomeScreenUiEvent.noteTitleChanged -> {
        state = state.copy(note = homeScreenUiEvent.description)
      }
    }
  }

  init {
      getAllNotes()
  }

//  fun getCurrentUser() {
//    viewModelScope.launch {
//      val currentUser = getCurrentUserUseCase()
//      _currentUser.value = currentUser
//    }
//  }

  init {
//    getCurrentUser()
  }


}
