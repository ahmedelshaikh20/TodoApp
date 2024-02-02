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
import com.example.domain.usecases.user.GetCurrentUserUseCase
import com.example.todoapp.ui.screens.homescreen.HomeScreenState
import com.example.todoapp.ui.screens.homescreen.HomeScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val getCurrentUser: GetCurrentUserUseCase,
  val addNote: AddNoteUseCase
) :
  ViewModel() {



   var state = MutableStateFlow(HomeScreenState(""))
  fun addNote() {
    viewModelScope.launch {
      try {
        val noteModel = NoteModel(state.value.note)
        addNote(noteModel)
      } catch (e: Exception) {
        Log.d("Error", e.message.toString())
      }
    }
  }

  fun onEvent(homeScreenUiEvent: HomeScreenUiEvent){
    when(homeScreenUiEvent){
      is HomeScreenUiEvent.noteTitleChanged ->
      {
        state.value = state.value.copy( note =homeScreenUiEvent.description )
      }
    }
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
