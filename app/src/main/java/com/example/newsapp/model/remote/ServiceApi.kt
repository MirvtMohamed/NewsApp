package com.example.newsapp.model.remote

import com.example.newsapp.model.entity.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980")
    fun getApiNews(): Call<Article>

    @GET("top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980&category={category}")
    fun getCategoryApiNews(@Path("category") category: String): Call<Article>

    @GET("top-headlines?country={country}&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980")
    suspend fun getApiNewsCountry(@Path("country") country: String): Call<Article>

}