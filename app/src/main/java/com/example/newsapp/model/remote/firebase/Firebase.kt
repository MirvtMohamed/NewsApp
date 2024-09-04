//package com.example.newsapp.model.remote.firebase
//
//import android.content.ContentValues.TAG
//import android.util.Log
//import com.example.newsapp.model.entity.NewsModel
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class Firebase {
//    private val db: FirebaseFirestore
//
//    init {
//         db = Firebase.firestore
//    }
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