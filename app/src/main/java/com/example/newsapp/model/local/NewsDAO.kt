package com.example.newsapp.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.entity.NewsModel


@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(news: NewsModel)

    @Delete
    suspend fun deleteNews(news: NewsModel)

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<NewsModel>
}