package com.example.newsapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.entity.NewsModel

@Database(entities = [NewsModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDAO

    companion object {
        // Singleton prevents multiple instances of database opening
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        private const val DATABASE_NAME = "news_database"

        // Get a database instance
        fun getInstance(context: Context): NewsDatabase {

            /*
            -- If the INSTANCE is not null, then return it,
            -- if it is, then create the database
            -- This is a thread-safe way to create a singleton
            -- synchronized means that only one thread can execute this block of code at a time
             */

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}