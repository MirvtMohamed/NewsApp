package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.remote.firebase.FirebaseHelper
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val trial= FirebaseHelper()
        trial.addNews(NewsModel("M","M","M"))

        lifecycleScope.launch {
            try {
             trial.getNews()
                // Handle the result here
            } catch (e: Exception) {
                // Handle exceptions here
            }
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}