package com.example.newsapp.model.remote


import com.example.newsapp.model.entity.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("top-headlines")
    suspend fun getCategoryApiNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "5b80b3bfe0e24c7a82ccb0a958f60980",
        @Query("category") category: String
    ): Response<Article>

    @GET("top-headlines")
    suspend fun getApiNewsCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "5b80b3bfe0e24c7a82ccb0a958f60980"
    ): Response<Article>
}




//
//interface ServiceApi {
//
//    @GET("top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980")
//    fun getApiNews(): Call<Article>
//
//    @GET("top-headlines?country=us&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980&category={category}")
//    fun getCategoryApiNews(@Path("category") category: String): Call<Article>



//
//    @GET("top-headlines?country={country}&apiKey=5b80b3bfe0e24c7a82ccb0a958f60980")
//    suspend fun getApiNewsCountry(@Path("country") country: String): Call<Article>

//}