package com.example.newsapp.model.remote

import com.example.newsapp.model.entity.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepositoryImp(private val api: ServiceApi): RemoteRepository {

    override suspend fun getApiNews() = withContext(Dispatchers.IO) {
        api.getApiNews()
    }

    override suspend fun getCategoryApiNews(category: String) = withContext(Dispatchers.IO) {
        api.getCategoryApiNews(category)
    }
}