package com.example.newsapp.ui.favoriteList

import android.app.Application
import android.util.Log
import android.view.WindowInsetsAnimation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.entity.Article
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.local.LocalRepositoryImp
import com.example.newsapp.model.local.NewsDatabase
import com.example.newsapp.model.remote.RetroBuilder
import com.example.newsapp.model.remote.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteNewsViewModel(application: Application) : AndroidViewModel(application) {
    private val service: ServiceApi

    init {
        service = RetroBuilder.getRetroInstance().create(ServiceApi::class.java)

    }



    fun getNewsApi() {
        service.getApiNews().enqueue(object : Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val news = response.body()
                val articles = news?.articles
                if (articles != null) {
                    for (article in articles) {
                        Log.d("RemoteNewsViewModel", "onResponse: ${article}")
                        }
                    }
                }


            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.e("RemoteNewsViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getCategoryNewsApi(category: String) {
        service.getCategoryApiNews(category).enqueue(object : Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val news = response.body()
                val articles = news?.articles
                if (articles != null) {
                    for (article in articles) {
                        Log.d("RemoteNewsViewModel", "onResponse: ${article}")
                    }
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.e("RemoteNewsViewModel", "onFailure: ${t.message}")
            }
        })
    }
}