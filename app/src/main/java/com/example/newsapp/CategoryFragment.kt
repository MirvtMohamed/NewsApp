package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.ui.favoriteList.RemoteNewsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get ViewModel instance
        val remoteNewsViewModel: RemoteNewsViewModel by viewModels()

        // Set up RecyclerView
        adapter = NewsAdapter(arrayListOf())
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(requireContext())

        // Fetch data and update the adapter
        lifecycleScope.launch {
            val articles = remoteNewsViewModel.getCategoryNewsApi("technology")
            articles?.let {
                adapter.articles.clear()  // Clear any previous data
                adapter.articles.addAll(it)  // Add new articles
                adapter.notifyDataSetChanged()  // Notify the adapter of data changes
            }
        }
    }
}
