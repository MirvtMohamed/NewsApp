package com.example.newsapp.ui.favoriteList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.local.LocalRepositoryImp
import com.example.newsapp.model.local.NewsDatabase
import com.example.newsapp.model.remote.RemoteRepositoryImp
import com.example.newsapp.model.remote.RetroBuilder
import com.example.newsapp.model.remote.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteNewsViewModel(application: Application) : AndroidViewModel(application) {
    private val remoteRepository: RemoteRepositoryImp

    init {
        val service = RetroBuilder.getRetroInstance().create(ServiceApi::class.java)
        remoteRepository = RemoteRepositoryImp(service)
    }

    private var newsApiMutableLiveData = MutableLiveData<List<NewsModel>>()
    val newsApiLiveData: LiveData<List<NewsModel>> get() = newsApiMutableLiveData

    fun getNewsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getApiNews()
            if (result.isSuccessful) {
                if (result.body() != null) {
                    newsApiMutableLiveData.postValue(result.body())
                }
            }
            else {
                Log.i("RemoteNewsViewModel", "getNewsApi: ${result.message()}")
            }

        }
    }

    fun getCategoryNews(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getCategoryApiNews(category)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    newsApiMutableLiveData.postValue(result.body())
                }
            }
            else {
                Log.i("RemoteNewsViewModel", "getCategoryNews: ${result.message()}")
            }
        }
    }
}