package com.example.todoapp.ui.screens.signinscreen

import com.example.domain.models.SignInModel
import com.example.domain.models.UserInfoModel

data class SignInScreenState (
  val currentUserInfo : UserInfoModel? = UserInfoModel("",""),
  var userSignInInfo : SignInModel = SignInModel("",""),
  val userSuccessfullyLogged : Boolean=false,
)
