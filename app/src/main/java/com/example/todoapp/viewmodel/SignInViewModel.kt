package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.SignInUseCase
import com.example.todoapp.ui.screens.signinscreen.SignInScreenState
import com.example.todoapp.utils.SignInUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
  val signInUseCase: SignInUseCase,
  val getCurrentUserUseCase: GetCurrentUserUseCase
) :
  ViewModel() {


  var signInScreenState by mutableStateOf(SignInScreenState())


  fun loginUser() {
    viewModelScope.launch {
      try {
        signInUseCase(signInScreenState.userSignInInfo)
        val user = getCurrentUserUseCase()
        signInScreenState = signInScreenState.copy(
          currentUserInfo = user
        )
        signInScreenState = signInScreenState.copy(
          userSuccessfullyLogged = true
        )
      } catch (e: Exception) {
        Log.e("SignInError", e.message.toString())
      }
    }
  }


  fun signinEventTriggered(signInUIEvent: SignInUIEvent) {

    when (signInUIEvent) {
      is SignInUIEvent.EmailChanged -> {
        signInScreenState.userSignInInfo = signInScreenState.userSignInInfo.copy(
          email = signInUIEvent.email
        )
      }

      is SignInUIEvent.PasswordChanged -> {
        signInScreenState.userSignInInfo = signInScreenState.userSignInInfo.copy(
          password = signInUIEvent.password
        )
      }
    }

  }

}
