package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserInfoModel
import com.example.domain.usecases.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel(){


  private val _currentUser: MutableStateFlow<UserInfoModel> =
    MutableStateFlow(UserInfoModel("", ""))
  val currentUser : StateFlow<UserInfoModel> = _currentUser


  private val _isLoggedIn : MutableStateFlow<Boolean?> = MutableStateFlow(null)
  val isLoggedIn:StateFlow<Boolean?> =_isLoggedIn

  init {
    getCurrentUser()
  }
  fun getCurrentUser(){
    viewModelScope.launch {
      try {
      val currentUser = getCurrentUserUseCase()
       if (currentUser?.fullName==null){
         _isLoggedIn.value = false}
        else {
      _currentUser.value = UserInfoModel(fullName = currentUser.fullName, email = currentUser.email)
      _isLoggedIn.value=true}
      }
      catch (e: Exception){
        Log.e("Error Fetching Current User" , e.message.toString())
      }
    }
  }


}
