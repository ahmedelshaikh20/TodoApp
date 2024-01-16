package com.example.data.repo

import android.util.Log
import com.example.domain.models.RegistrationModel
import com.example.domain.models.UserInfoModel
import com.example.domain.repositories.RemoteRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor( val firebaseAuth: FirebaseAuth) : RemoteRepo {
  //Repo Talks to client and then the client make the call
  override fun sendUserDetailsToServer(registrationModel: RegistrationModel) {

    firebaseAuth.createUserWithEmailAndPassword(registrationModel.email, registrationModel.password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          Log.d("SignUp", "createUserWithEmail:success")
          val user = firebaseAuth.currentUser
          val profileUpdates = userProfileChangeRequest {
            displayName = registrationModel.fullName
          }
          user?.updateProfile(profileUpdates)?.addOnCompleteListener {
            Log.d("User Profile Updated" , " Done" )
          }
        } else {
          Log.w("SignUp", "createUserWithEmail:failure", task.exception)
        }
      }
  }

  override fun getCurrentUserFromServer(): FirebaseUser? {
    return firebaseAuth.currentUser

  }
}
