package com.example.newsapp.ui.favoriteList

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.remote.RetroBuilder


class RemoteNewsViewModel : ViewModel() {

    private val service = RetroBuilder.api

    // Function to get news by category
    suspend fun getCategoryNewsApi(category: String): List<NewsModel> {
        return try {
            // Make the API call
            val response = service.getCategoryApiNews(category = category)

            // Check if response is successful and body is not null
            if (response.isSuccessful) {
                response.body()?.articles ?: emptyList()
            } else {
                Log.e("RemoteNewsViewModel", "Error: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("RemoteNewsViewModel", "Exception: ${e.message}")
            emptyList()
        }
    }
}





//class RemoteNewsViewModel(application: Application) : AndroidViewModel(application) {
//    private val service: ServiceApi = RetroBuilder.getRetroInstance().create(ServiceApi::class.java)
//
//
//    fun getNewsApi() {
//        service.getApiNews().enqueue(object : Callback<Article> {
//            override fun onResponse(call: Call<Article>, response: Response<Article>) {
//                val news = response.body()
//                val articles = news?.articles
//                if (articles != null) {
//                    for (article in articles) {
//                        Log.d("RemoteNewsViewModel", "onResponse: ${article}")
//                        }
//                    }
//                }
//
//
//            override fun onFailure(call: Call<Article>, t: Throwable) {
//                Log.e("RemoteNewsViewModel", "onFailure: ${t.message}")
//            }
//        })
//    }

//    fun getCategoryNewsApi(category: String) : List<NewsM> {
//        var articles: List<NewsM> = emptyList()
//        service.getCategoryApiNews(category).enqueue(object : Callback<Article> {
//            override fun onResponse(call: Call<Article>, response: Response<Article>) {
//                val news = response.body()
//                articles = news?.articles ?: emptyList()
//            }
//
//            override fun onFailure(call: Call<Article>, t: Throwable) {
//                Log.e("RemoteNewsViewModel", "onFailure: ${t.message}")
//            }
//        })
//        return articles
//    }


//}