package com.example.domain.usecases.user

import com.example.domain.repositories.UserRepo
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val userRepo: UserRepo) {
operator suspend fun invoke() = userRepo.getCurrentUser()

}
