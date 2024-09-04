package com.example.newsapp.model.remote

import com.example.newsapp.model.entity.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("/top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980")
    suspend fun getApiNews(): Response<List<NewsModel>>

    @GET("/top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980&category={category}")
    suspend fun getCategoryApiNews(@Path("category") category: String): Response<List<NewsModel>>
}