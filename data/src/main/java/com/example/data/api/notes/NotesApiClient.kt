package com.example.data.api.notes

import android.util.Log
import com.example.domain.models.NoteModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class NotesApiClient @Inject constructor(
  val firebaseAuth: FirebaseAuth,
  val firebaseFirestore: FirebaseFirestore
) {

 fun addNote(note : NoteModel){
   val user = firebaseAuth.currentUser
   firebaseFirestore.collection(user?.uid.toString())
     .document("note")
     .set(note)
     .addOnSuccessListener { Log.d("Add Note", "DocumentSnapshot successfully written!") }
     .addOnFailureListener { e -> Log.w("Add Note", "Error writing document", e) }
 }

}
