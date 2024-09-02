package com.example.newsapp.model.local

import com.example.newsapp.model.entity.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db:NewsDatabase): LocalRepository {
    override suspend fun addNews(news: NewsModel) {
        // withContext is a function that changes the context of the coroutine
        // Dispatchers.IO is a thread pool that is optimized for disk IO operations
        withContext(Dispatchers.IO) {
            db.newsDao().addNews(news)
        }
    }

    override suspend fun deleteNews(news: NewsModel) {
        withContext(Dispatchers.IO) {
            db.newsDao().deleteNews(news)
        }
    }

    override suspend fun getNews() = withContext(Dispatchers.IO) {
        db.newsDao().getNews()
    }
}

/*
code when need to use the local repository
val localRepository = LocalRepositoryImp(NewsDatabase.getInstance(requireContext()))
-- inside onClickListener
-- suspend function should be called from a coroutine scope
-- not allowed to use ui in the coroutine scope
GlobalCoroutineScope(Dispatchers.IO).launch {
    localRepository.addNews(news)
}
 */