package com.example.todoapp.ui.screens.homescreen

sealed class HomeScreenUiEvent() {

  data class noteTitleChanged (val description :String) :HomeScreenUiEvent()

}
