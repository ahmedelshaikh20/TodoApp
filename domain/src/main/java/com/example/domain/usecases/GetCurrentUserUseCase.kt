package com.example.domain.usecases

import com.example.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(val remoteRepo: RemoteRepo) {
operator fun invoke() = remoteRepo.getCurrentUserFromServer()

}
