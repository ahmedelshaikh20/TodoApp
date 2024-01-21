package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.SignInModel
import com.example.domain.models.UserInfoModel
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.SignInUseCase
import com.example.todoapp.utils.SignInUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val signInUseCase: SignInUseCase ,
  val getCurrentUserUseCase: GetCurrentUserUseCase) :
  ViewModel() {

  private val _userSignInInfo: MutableStateFlow<SignInModel> = MutableStateFlow(
    SignInModel("", "")
  )
  var userSignInInfo = _userSignInInfo

  private val _userSuccessfullySignIn :MutableStateFlow<Boolean> = MutableStateFlow(false)
  val userSuccessfullySignIn =_userSuccessfullySignIn

  private val _currentUser: MutableStateFlow<UserInfoModel?> =
    MutableStateFlow(UserInfoModel("", ""))
  val currentUser : StateFlow<UserInfoModel?> = _currentUser

  fun loginUser() {
    viewModelScope.launch {
      try {
        signInUseCase(userSignInInfo.value)
        val user = getCurrentUserUseCase()
        _currentUser.value = user
        Log.e("SignInError", currentUser.value?.fullName.toString())

        _userSuccessfullySignIn.value = true
      } catch (e: Exception) {
        Log.e("SignInError", e.message.toString())
      }
    }
  }


  fun signinEventTriggered(signInUIEvent: SignInUIEvent) {

    when (signInUIEvent) {
      is SignInUIEvent.EmailChanged -> {
        _userSignInInfo.value = _userSignInInfo.value.copy(
          email = signInUIEvent.email
        )
      }

      is SignInUIEvent.PasswordChanged -> {
        _userSignInInfo.value = _userSignInInfo.value.copy(
          password = signInUIEvent.password
        )
      }
    }

  }

}
