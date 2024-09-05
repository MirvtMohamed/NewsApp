//package com.example.newsapp.model.remote.firebase
//
//import android.app.Application
//import android.content.ContentValues.TAG
//import android.util.Log
//import androidx.core.content.ContentProviderCompat.requireContext
//import com.example.newsapp.model.entity.NewsModel
//import com.google.firebase.FirebaseApp
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class FirebaseHelper (application: Application){
//
//    val db = Firebase.firestore
//
//    fun addNews(news: NewsModel) {
//        db.collection("news")
//            .add(news)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//    }
//
//    suspend fun getNews() = withContext(Dispatchers.IO) {
//        db.collection("news")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }
//    }
//}