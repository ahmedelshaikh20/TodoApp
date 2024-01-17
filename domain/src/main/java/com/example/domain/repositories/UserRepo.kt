package com.example.domain.repositories

import com.example.domain.models.RegistrationModel
import com.example.domain.models.UserInfoModel

interface UserRepo {
  suspend fun registerUser(registrationModel: RegistrationModel)
  suspend fun getCurrentUser():UserInfoModel


}
