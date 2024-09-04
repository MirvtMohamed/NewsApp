package com.example.newsapp.ui.favoriteList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.local.LocalRepositoryImp
import com.example.newsapp.model.local.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val localRepository: LocalRepositoryImp
    private val newsMutableLiveData = MutableLiveData<List<NewsModel>>()
    val newsLiveData:LiveData<List<NewsModel>> get() = newsMutableLiveData

    init {
        val newsDatabase = NewsDatabase.getInstance(application)
        localRepository = LocalRepositoryImp(newsDatabase)
    }

    fun addNews(news: NewsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.addNews(news)
        }
    }

    fun deleteNews(news: NewsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.deleteNews(news)
        }
    }

    fun getNews() =
        viewModelScope.launch(Dispatchers.IO) {
            newsMutableLiveData.postValue(localRepository.getNews())
        }
}