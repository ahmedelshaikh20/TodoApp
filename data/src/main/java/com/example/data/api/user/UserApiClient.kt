package com.example.data.api.user

import android.util.Log
import com.example.domain.models.RegistrationModel
import com.example.domain.models.SignInModel
import com.example.domain.models.UserInfoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserApiClient @Inject constructor(private val firebaseAuth: FirebaseAuth) {

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

  suspend fun signInUser(signInModel: SignInModel) = suspendCoroutine { continuation ->
    firebaseAuth.signInWithEmailAndPassword(signInModel.email, signInModel.password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          Log.d("SignIn", "currentUser Name : ${firebaseAuth.currentUser?.displayName}")
          continuation.resume(Unit)
        }
      }
      .addOnFailureListener {
        continuation.resumeWithException(it)
      }

  }


  suspend fun updateUserName(fullName: String): Unit = suspendCoroutine { continuation ->
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


  suspend fun getCurrentUser(): UserInfoModel? {

    try {
      val user = firebaseAuth.currentUser
      return user?.toUserInfoModel()
    } catch (e: Exception) {

      throw Exception(e.message)
    }
  }
}

// mapping function from FirebaseUser to UserModel
fun FirebaseUser.toUserInfoModel(): UserInfoModel {
  return UserInfoModel(displayName,email)
}
