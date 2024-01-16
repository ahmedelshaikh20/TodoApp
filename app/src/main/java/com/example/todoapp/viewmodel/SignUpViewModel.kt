package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.RegistrationModel
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.SignUpUseCase
import com.example.todoapp.utils.SignUpUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  val signUpUseCase: SignUpUseCase,
  val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

  private val _userRegistrationInfo: MutableStateFlow<RegistrationModel?> = MutableStateFlow(
    RegistrationModel()
  )
  val userRegistrationInfo: StateFlow<RegistrationModel?> = _userRegistrationInfo
  fun signUp() {
    if (validateRegistrationInfo())
      userRegistrationInfo.value?.let { signUpUseCase(it) }
    else
      Log.e("SignUp Error", "SignUp Error")
  }

  private fun validateRegistrationInfo(): Boolean {
    if (userRegistrationInfo.value?.password == null || userRegistrationInfo.value?.fullName == null || userRegistrationInfo.value?.password == null)
      return false
    else
      return true
  }

//fun getCurrentUser(){
//  val currentUser = getCurrentUserUseCase()
//  _user.value = UserInfoModel(currentUser?.displayName , email = currentUser?.email)
//  Log.d("currenttt", currentUser?.email.toString())
//}


  fun signUpEventsTriggered(event: SignUpUIEvent) {
    // This part i didnt do it myself i saw someone doing it 
    when (event) {

      is SignUpUIEvent.FullNameChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value?.copy(
          fullName = event.fullname
        )
      }

      is SignUpUIEvent.EmailChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value?.copy(
          email = event.email
        )
      }

      is SignUpUIEvent.PasswordChanged -> {
        _userRegistrationInfo.value = _userRegistrationInfo.value?.copy(
          password = event.password
        )

      }

    }
  }

  init {

  }
}
