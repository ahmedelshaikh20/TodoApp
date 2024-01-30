package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.user.GetCurrentUserUseCase
import com.example.todoapp.ui.screens.splashscreen.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val currentUser: GetCurrentUserUseCase) :
  ViewModel() {


   var splashScreenState by mutableStateOf(SplashScreenState(isLogged = false , currentUserName = null))

  init {
    getCurrentUser()
  }

  fun getCurrentUser() {
    viewModelScope.launch {
      try {
        val currUser = currentUser()
        splashScreenState = if (currUser?.fullName == null) {
          splashScreenState.copy(isLogged = false)
        } else {
          splashScreenState.copy(isLogged = true, currentUserName = currUser.fullName)
        }
      } catch (e: Exception) {
        Log.e("Error Fetching Current User", e.message.toString())
      }
    }
  }


}
