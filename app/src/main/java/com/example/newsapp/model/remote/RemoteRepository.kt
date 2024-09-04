package com.example.newsapp.model.remote

import com.example.newsapp.model.entity.NewsModel
import retrofit2.Response

interface RemoteRepository {
    suspend fun getApiNews() : Response<List<NewsModel>>
    suspend fun getCategoryApiNews(category: String) : Response<List<NewsModel>>
}