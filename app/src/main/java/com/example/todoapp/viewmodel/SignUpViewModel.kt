package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.RegistrationModel
import com.example.domain.usecases.SignUpUseCase
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

  private val _userRegistrationInfo: MutableStateFlow<RegistrationModel> = MutableStateFlow(
    RegistrationModel("", "", "")
  )
  val userRegistrationInfo = _userRegistrationInfo


  private val _isRegistrationDone: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val isRegistrationDone: StateFlow<Boolean> = _isRegistrationDone
  fun signUp() {
    viewModelScope.launch {
      try {
        validateRegistrationInfo()
        Log.e("SignUp Error", _isRegistrationDone.value.toString())
        signUpUseCase(userRegistrationInfo.value)
        _isRegistrationDone.value = true

      } catch (e: Exception) {
        Log.e("SignUp Error", e.message.toString())
      }

    }
  }

  private fun validateRegistrationInfo() {
    if (userRegistrationInfo.value.password == "" || userRegistrationInfo.value.fullName == "" || userRegistrationInfo.value.password == "") {
      throw Exception("Some Fields are missing")
    }
  }


  fun signUpEventsTriggered(event: SignUpUIEvent) {

    when (event) {

      is SignUpUIEvent.FullNameChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
          fullName = event.fullname
        )
      }

      is SignUpUIEvent.EmailChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
          email = event.email
        )
      }

      is SignUpUIEvent.PasswordChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
          password = event.password
        )

      }

    }
  }

  init {

  }
}
