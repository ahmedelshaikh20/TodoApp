package com.example.domain.usecases

import com.example.domain.models.RegistrationModel
import com.example.domain.repositories.RemoteRepo
import javax.inject.Inject

class SignUpUseCase @Inject constructor (private val remoteRepo: RemoteRepo) {
// UseCase Talks to repo
  operator fun invoke(registrationModel: RegistrationModel)=remoteRepo.sendUserDetailsToServer(registrationModel)

}
