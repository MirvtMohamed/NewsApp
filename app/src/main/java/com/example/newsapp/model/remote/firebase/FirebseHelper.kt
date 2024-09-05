package com.example.newsapp.model.remote.firebase

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.newsapp.model.entity.NewsFirebaseModel
import com.example.newsapp.model.entity.NewsModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseHelper (){

    val db = Firebase.firestore

    fun addNews(news: NewsModel) {
        db.collection("news")
            .add(news)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    // make this function return a list of NewsFirebaseModel

    suspend fun getNews(): List<NewsFirebaseModel> = withContext(Dispatchers.IO) {
        val newsList = mutableListOf<NewsFirebaseModel>()
        try {
            val result = db.collection("news").get().await()
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")

                // Extract fields from document data
                val title = document.getString("title") ?: ""
                val url = document.getString("url") ?: ""
                val urlToImage = document.getString("urlToImage") ?: ""

                // Map document data to NewsFirebaseModel
                val newsfirebaseModel = NewsFirebaseModel(
                    id = document.id,
                    title = title,
                    url = url,
                    urlToImage = urlToImage
                )
                newsList.add(newsfirebaseModel)
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error getting documents.", e)
        }
        return@withContext newsList
    }


    suspend fun deleteNews(id:String)  = withContext(Dispatchers.IO){
        db.collection("news").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}