package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.model.entity.NewsFirebaseModel
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.remote.firebase.FirebaseHelper
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.techCategoryImage.setOnClickListener {
            navController.navigate(R.id.categoryFragment2)
        }
        binding.sportsCategory.setOnClickListener {
            navController.navigate(R.id.categoryFragment2)
        }
        binding.entertainmentCategory.setOnClickListener {
            navController.navigate(R.id.categoryFragment2)
        }
        // Inflate the layout for this fragment
        return view
    }



}