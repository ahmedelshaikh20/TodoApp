package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.user.GetCurrentUserUseCase
import com.example.todoapp.ui.screens.splashscreen.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val currentUser: GetCurrentUserUseCase) :
  ViewModel() {


  var state =
    MutableStateFlow(SplashScreenState(isLogged = false, currentUserName = null))

  init {
    getCurrentUser()
  }

  fun getCurrentUser() {
    viewModelScope.launch {
      try {
        val currUser = currentUser()
        state.value = if (currUser?.fullName == null) {
          state.value.copy(isLogged = false)
        } else {
          state.value.copy(isLogged = true, currentUserName = currUser.fullName)
        }
      } catch (e: Exception) {
        Log.e("Error Fetching Current User", e.message.toString())
      }
    }
  }


}
