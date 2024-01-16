package com.example.domain.repositories

import com.example.domain.models.RegistrationModel
import com.google.firebase.auth.FirebaseUser

interface RemoteRepo {
  fun sendUserDetailsToServer(registrationModel: RegistrationModel )
  fun getCurrentUserFromServer():FirebaseUser?


}
