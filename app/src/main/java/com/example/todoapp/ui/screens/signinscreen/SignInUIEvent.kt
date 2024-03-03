package com.example.todoapp.ui.screens.signinscreen

sealed class SignInUIEvent {
  data class EmailChanged(val email: String) : SignInUIEvent()
  data class PasswordChanged(val password: String) : SignInUIEvent()

}
