package com.example.todoapp.utils

//
//enum class SignUpEvent {
//  FULL_NAME_CHANGED,
//  EMAIL_CHANGED,
//  PASSWORD_CHANGED,
//}


sealed class SignUpUIEvent {
  data class FullNameChanged(val fullname:String) : SignUpUIEvent()
  data class EmailChanged(val email:String) : SignUpUIEvent()
  data class PasswordChanged(val password:String) : SignUpUIEvent()

}
