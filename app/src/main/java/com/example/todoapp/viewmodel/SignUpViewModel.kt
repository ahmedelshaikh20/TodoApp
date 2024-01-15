package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.RegistrationModel
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.SignUpUseCase
import com.example.todoapp.utils.SignUpEvent
import com.example.todoapp.utils.SignUpUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val signUpUseCase: SignUpUseCase , val getCurrentUserUseCase :GetCurrentUserUseCase ) : ViewModel() {

  private val _userRegistrationInfo :MutableStateFlow<RegistrationModel> = MutableStateFlow(RegistrationModel("ahmed", "salah" , ".com" , "lool"))
  val userRegistrationInfo : StateFlow<RegistrationModel> = _userRegistrationInfo
   fun signUp() {
    if (validateRegistrationInfo())
    signUpUseCase(userRegistrationInfo.value)
    else
        Log.e("SignUp Error"  , "Make sure password are identical")
  }

  private fun validateRegistrationInfo(): Boolean {
      if (userRegistrationInfo.value?.email==null ||userRegistrationInfo.value?.password==null || userRegistrationInfo.value?.passwordConfirmation==null ||userRegistrationInfo.value?.fullName==null)
      return false
    else if(userRegistrationInfo.value?.password!=userRegistrationInfo.value?.passwordConfirmation)
      return false
    else
      return true
  }

//fun getCurrentUser(){
//  val currentUser = getCurrentUserUseCase()
//  _user.value = UserInfoModel(currentUser?.displayName , email = currentUser?.email)
//  Log.d("currenttt", currentUser?.email.toString())
//}


fun signUpEventsTriggered(signUpUIEvent: SignUpUIEvent){
  when(signUpUIEvent.signUpEvent){
    SignUpEvent.FULL_NAME_CHANGED -> {
      _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
        fullName = signUpUIEvent.parameter
      )
    }
    SignUpEvent.EMAIL_CHANGED -> {
      _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
        email = signUpUIEvent.parameter
      )
    }
    SignUpEvent.PASSWORD_CHANGED -> {
      _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
         password = signUpUIEvent.parameter
      )
    }
    SignUpEvent.PASSWORD_CONFIRMATION_CHANGED -> {
      _userRegistrationInfo.value = _userRegistrationInfo.value.copy(
        passwordConfirmation = signUpUIEvent.parameter
      )
    }
  }
}

  init {

  }
}
