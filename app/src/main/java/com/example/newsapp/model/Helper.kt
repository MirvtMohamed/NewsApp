package com.example.newsapp.model

import androidx.activity.viewModels
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.ui.favoriteList.NewsViewModel
import com.example.newsapp.ui.favoriteList.RemoteNewsViewModel

class Helper {
    // add data to room database
//    val newsModel = NewsModel(
//        "newsTitle",
//        "any url",
//        "ghjk",
//    )
//    val viewModel: NewsViewModel by viewModels()
//    viewModel.addNews(newsModel)
//    // get data from room database
//    viewModel.viewModelScope.launch {
//        viewModel.getNews()
//    }
//    viewModel.viewModelScope.launch {
//        viewModel.newsLiveData.observe(this@MainActivity) {
//            // print data from room database
//            it.forEach { newsModel ->
//                println(newsModel)
//            }
//        }
//    }
//    // delete data from room database
//    viewModel.viewModelScope.launch {
//        viewModel.deleteNews(newsModel)
//        println("model deleted")
//    }
//
//    viewModel.viewModelScope.launch {
//        viewModel.getNews()
//    }
//    viewModel.viewModelScope.launch {
//        viewModel.newsLiveData.observe(this@MainActivity) {
//            // print data from room database
//            it.forEach { newsModel ->
//                println(newsModel)
//            }
//        }
//    }
//}


    // for api
//    btn.setOnClickListener {
//        val remoteNewsViewModel: RemoteNewsViewModel by viewModels()
//        remoteNewsViewModel.getCategoryNewsApi("sports")
//    }
}