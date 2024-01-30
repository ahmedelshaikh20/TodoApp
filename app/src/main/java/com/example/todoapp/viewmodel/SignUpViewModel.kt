package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.user.SignUpUseCase
import com.example.todoapp.ui.screens.signupscreen.SignUpScreenState
import com.example.todoapp.ui.screens.signupscreen.SignUpUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  val signUp: SignUpUseCase,
) : ViewModel() {


  var state by mutableStateOf(SignUpScreenState())

  fun signUp() {
    viewModelScope.launch {
      try {

        validateRegistrationInfo()
        signUp(state.userRegistrationInfo)
        state = state.copy(isRegistrationDone = true)
        Log.e("SignUp Success", state.userRegistrationInfo.toString())
      } catch (e: Exception) {
        Log.e("SignUp Error", e.message.toString())
      }

    }
  }

  private fun validateRegistrationInfo() {
    val userRegistrationInfo = state.userRegistrationInfo
    if (userRegistrationInfo.email == "" || userRegistrationInfo.fullName == "" || userRegistrationInfo.password == "") {
      throw Exception("Some Fields are missing")
    }
  }


  fun signUpEventsTriggered(event: SignUpUIEvent) {

    when (event) {

      is SignUpUIEvent.FullNameChanged -> {
        state.userRegistrationInfo = state.userRegistrationInfo.copy(
          fullName = event.fullname
        )
      }

      is SignUpUIEvent.EmailChanged -> {
        state.userRegistrationInfo = state.userRegistrationInfo.copy(
          email = event.email
        )
      }

      is SignUpUIEvent.PasswordChanged -> {
        state.userRegistrationInfo = state.userRegistrationInfo.copy(
          password = event.password
        )

      }

    }
  }


}
