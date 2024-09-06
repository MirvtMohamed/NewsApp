package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using data binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()


        binding.sportsCategoryCard.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment("sports")
            navController.navigate(action)
        }

        binding.technologyCategoryCard.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment("technology")
            navController.navigate(action)
        }

        binding.entertainmentCategoryCard.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment("entertainment")
            navController.navigate(action)
        }
    }
}
