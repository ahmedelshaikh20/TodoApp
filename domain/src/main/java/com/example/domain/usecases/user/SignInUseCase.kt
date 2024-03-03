package com.example.domain.usecases.user

import com.example.domain.models.SignInModel
import com.example.domain.models.UserInfoModel
import com.example.domain.repositories.UserRepo
import javax.inject.Inject

class SignInUseCase @Inject constructor(val userRepo:UserRepo)  {

  suspend operator fun invoke(signInModel: SignInModel) = userRepo.signInUser(signInModel)

}
