package com.example.todoapp.utils



sealed class SignUpUIEvent {
  data class FullNameChanged(val fullname:String) : SignUpUIEvent()
  data class EmailChanged(val email:String) : SignUpUIEvent()
  data class PasswordChanged(val password:String) : SignUpUIEvent()

}
