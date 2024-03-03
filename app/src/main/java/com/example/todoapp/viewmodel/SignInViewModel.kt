package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.SignInModel
import com.example.domain.usecases.user.GetCurrentUserUseCase
import com.example.domain.usecases.user.SignInUseCase
import com.example.todoapp.ui.screens.signinscreen.SignInScreenState
import com.example.todoapp.ui.screens.signinscreen.SignInUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
  val signIn: SignInUseCase,
  val getCurrentUser: GetCurrentUserUseCase
) :
  ViewModel() {


  var state = MutableStateFlow(SignInScreenState("", "", "", false))


  fun loginUser() {
    viewModelScope.launch {
      try {
        val userInfo = SignInModel(state.value.email, state.value.password)
        signIn(userInfo)
        val user = getCurrentUser()
        user?.let {
          state.value = state.value.copy(
            currentUserName = it.fullName
          )
        }
        state.value = state.value.copy(
          userSuccessfullyLogged = true
        )
      } catch (e: Exception) {
        Log.e("SignInError", e.message.toString())
      }
    }
  }


  fun signinEventTriggered(event: SignInUIEvent) {

    when (event) {
      is SignInUIEvent.EmailChanged -> {
        state.value = state.value.copy(
          email = event.email
        )
      }

      is SignInUIEvent.PasswordChanged -> {
        state.value = state.value.copy(
          password = event.password
        )
      }
    }

  }

}
