package com.example.data.repo

import android.annotation.SuppressLint
import android.util.Log
import com.example.data.api.user.UserApiClient
import com.example.domain.models.RegistrationModel
import com.example.domain.models.UserInfoModel
import com.example.domain.repositories.UserRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class UserRepoImpl @Inject constructor(val userApiClient: UserApiClient) : UserRepo {
  //Repo Talks to client and then the client make the call
  override suspend fun registerUser(registrationModel: RegistrationModel) {
    userApiClient.registerUser(registrationModel)
userApiClient.updateUserName(registrationModel.fullName)
  }


  override suspend fun getCurrentUser(): UserInfoModel {
 val firebaseUser =  userApiClient.getCurrentUser()
    return UserInfoModel(firebaseUser?.displayName , firebaseUser?.email)

  }



}

