package com.example.newsapp.model.local

import com.example.newsapp.model.entity.NewsModel

interface LocalRepository {

    suspend fun addNews(news: NewsModel)

    suspend fun deleteNews(news: NewsModel)

    suspend fun getNews(): List<NewsModel>
}