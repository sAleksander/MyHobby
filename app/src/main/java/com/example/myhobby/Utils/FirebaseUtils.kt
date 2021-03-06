package com.example.myhobby.Utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseDatabase = Firebase.database
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}