package com.example.todoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.todoapp.ui.screens.splashscreen.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val getCurrentUserUseCase: GetCurrentUserUseCase) :
  ViewModel() {


   var splashScreenState by mutableStateOf(SplashScreenState())

  init {
    getCurrentUser()
  }

  fun getCurrentUser() {
    viewModelScope.launch {
      try {
        val currentUser = getCurrentUserUseCase()
        splashScreenState = if (currentUser?.fullName == null) {
          splashScreenState.copy(isLogged = false)
        } else {
          splashScreenState.copy(isLogged = true, currentUserName = currentUser.fullName)
        }
      } catch (e: Exception) {
        Log.e("Error Fetching Current User", e.message.toString())
      }
    }
  }


}
