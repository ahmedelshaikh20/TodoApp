package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.RegistrationModel
import com.example.domain.usecases.SignUpUseCase
import com.example.todoapp.ui.screens.signupscreen.SignUpScreenState
import com.example.todoapp.utils.SignUpUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  val signUpUseCase: SignUpUseCase,
) : ViewModel() {


  var signupScreenState by mutableStateOf(SignUpScreenState())

  fun signUp() {
    viewModelScope.launch {
      try {

        validateRegistrationInfo()
        signUpUseCase(signupScreenState.userRegistrationInfo)
        signupScreenState = signupScreenState.copy(isRegistrationDone = true)
        Log.e("SignUp Success", signupScreenState.userRegistrationInfo.toString())
      } catch (e: Exception) {
        Log.e("SignUp Error", e.message.toString())
      }

    }
  }

  private fun validateRegistrationInfo() {
    val userRegistrationInfo = signupScreenState.userRegistrationInfo
    if (userRegistrationInfo.email == "" || userRegistrationInfo.fullName == "" || userRegistrationInfo.password == "") {
      throw Exception("Some Fields are missing")
    }
  }


  fun signUpEventsTriggered(event: SignUpUIEvent) {

    when (event) {

      is SignUpUIEvent.FullNameChanged -> {
        signupScreenState.userRegistrationInfo = signupScreenState.userRegistrationInfo.copy(
          fullName = event.fullname
        )
      }

      is SignUpUIEvent.EmailChanged -> {
        signupScreenState.userRegistrationInfo = signupScreenState.userRegistrationInfo.copy(
          email = event.email
        )
      }

      is SignUpUIEvent.PasswordChanged -> {
        signupScreenState.userRegistrationInfo = signupScreenState.userRegistrationInfo.copy(
          password = event.password
        )

      }

    }
  }


}
