package com.example.todoapp.ui.screens.signupscreen

import com.example.domain.models.RegistrationModel

data class SignUpScreenState(
  val email: String,
  val fullName: String,
  val password: String,
  val isRegistrationDone: Boolean
)
