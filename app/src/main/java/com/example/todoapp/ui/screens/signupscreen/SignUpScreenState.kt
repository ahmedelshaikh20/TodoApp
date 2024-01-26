package com.example.todoapp.ui.screens.signupscreen

import com.example.domain.models.RegistrationModel

data class SignUpScreenState(
  var userRegistrationInfo: RegistrationModel = RegistrationModel("", "", ""),
  val isRegistrationDone: Boolean = false
)
