package com.example.domain.usecases

import com.example.domain.repositories.UserRepo
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(val userRepo: UserRepo) {
operator suspend fun invoke() = userRepo.getCurrentUser()

}
