package com.example.data.repo

import com.example.data.api.user.UserApiClient
import com.example.data.api.user.toUserInfoModel
import com.example.domain.models.RegistrationModel
import com.example.domain.models.SignInModel
import com.example.domain.models.UserInfoModel
import com.example.domain.repositories.UserRepo
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiClient: UserApiClient) : UserRepo {
  //Repo Talks to client and then the client make the call
  override suspend fun registerUser(registrationModel: RegistrationModel) {
    userApiClient.registerUser(registrationModel)
    userApiClient.updateUserName(registrationModel.fullName)
  }


  override suspend fun getCurrentUser(): UserInfoModel? {
    val firebaseUser = userApiClient.getCurrentUser()
    return firebaseUser?.toUserInfoModel()
  }

  override suspend fun signInUser(signInModel: SignInModel) {
    userApiClient.signInUser(signInModel)


  }




}

