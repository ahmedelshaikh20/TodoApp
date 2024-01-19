package com.example.todoapp.utils

sealed class SignInUIEvent {
  data class EmailChanged(val email: String) : SignInUIEvent()
  data class PasswordChanged(val password: String) : SignInUIEvent()

}
