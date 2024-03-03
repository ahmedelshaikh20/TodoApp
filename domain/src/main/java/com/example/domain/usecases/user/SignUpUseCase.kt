package com.example.domain.usecases.user

import com.example.domain.models.RegistrationModel
import com.example.domain.repositories.UserRepo
import javax.inject.Inject

class SignUpUseCase @Inject constructor (private val userRepo: UserRepo) {
// UseCase Talks to repo
  operator suspend fun invoke(registrationModel: RegistrationModel)=userRepo.registerUser(registrationModel)

}
