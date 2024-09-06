package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.model.entity.NewsFirebaseModel
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

        var sportsCategory: CardView = view?.findViewById(R.id.sportsCategory) as CardView
        var techCategory: CardView = view?.findViewById(R.id.techCategory) as CardView
        var entertainmentCategory: CardView = view?.findViewById(R.id.entertainmentCategory) as CardView

        sportsCategory.setOnClickListener {

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



}