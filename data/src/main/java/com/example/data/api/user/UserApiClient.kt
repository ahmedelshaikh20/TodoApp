package com.example.data.api.user

import android.util.Log
import com.example.domain.models.RegistrationModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserApiClient @Inject constructor(val firebaseAuth: FirebaseAuth) {

  suspend fun registerUser(registrationModel: RegistrationModel) =
    suspendCoroutine { continuation ->
      firebaseAuth.createUserWithEmailAndPassword(
        registrationModel.email,
        registrationModel.password
      )
        .addOnCompleteListener { task ->
          if (task.isSuccessful) {
            Log.d("SignUp", "createUserWithEmail:success")
            continuation.resume(Unit)
          }
        }
        .addOnFailureListener {
          continuation.resumeWithException(it)
        }
    }


  suspend fun updateUserName(fullName: String): Unit = suspendCoroutine {continuation->
    try {
      val user = firebaseAuth.currentUser
      val profileUpdates = userProfileChangeRequest {
        displayName = fullName
      }
      user?.updateProfile(profileUpdates)?.addOnCompleteListener {
        Log.d("User Profile Updated", " Done")
        continuation.resume(Unit)
      }
    } catch (e: Exception) {
      continuation.resumeWithException(e)

    }
  }


  suspend fun getCurrentUser(): FirebaseUser? = suspendCoroutine {

    try {
      val user = firebaseAuth.currentUser
      it.resume(user)

    } catch (e: Exception) {
      it.resumeWithException(e)

    }
  }
}
