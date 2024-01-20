package com.example.todoapp.viewmodel

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
class HomeViewModel @Inject constructor(val getCurrentUserUseCase: GetCurrentUserUseCase) :
  ViewModel() {


  private val _currentUser: MutableStateFlow<UserInfoModel?> =
    MutableStateFlow(UserInfoModel("", ""))
  val currentUser = _currentUser


  fun getCurrentUser() {
    viewModelScope.launch {
      val currentUser = getCurrentUserUseCase()
      _currentUser.value = currentUser
    }
  }

  init {
    getCurrentUser()
  }


}
