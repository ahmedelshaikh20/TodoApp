package com.example.todoapp.ui.screens.signinscreen

data class SignInScreenState(
  val email: String,
  val currentUserName: String?,
  val password: String,
  val userSuccessfullyLogged: Boolean ,
)
