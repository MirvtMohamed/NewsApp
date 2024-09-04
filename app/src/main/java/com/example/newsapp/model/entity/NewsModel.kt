package com.example.newsapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class NewsModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)