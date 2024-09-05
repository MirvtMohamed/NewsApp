package com.example.newsapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Article(
    val articles: List<NewsModel>
)


@Entity(tableName = "news")
data class NewsModel (
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
)

data class NewsFirebaseModel(
    val id: String,
    val title: String,
    val url: String,
    val urlToImage: String,
)